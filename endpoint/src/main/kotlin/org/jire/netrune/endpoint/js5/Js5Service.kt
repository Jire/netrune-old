package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.DecoderException
import org.jctools.queues.MessagePassingQueue
import org.jctools.queues.SpscArrayQueue
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.DecodeMessage
import org.jire.netrune.endpoint.Js5Responses
import org.jire.netrune.endpoint.Session

class Js5Service(
    private val js5Responses: Js5Responses
) : AbstractService(7) {

    private val prefetchQueue: MessagePassingQueue<ByteBuf> = SpscArrayQueue(QUEUE_CAPACITY)
    private val onDemandQueue: MessagePassingQueue<ByteBuf> = SpscArrayQueue(QUEUE_CAPACITY)

    private var loggedIn = false

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: DecodeMessage) {
        when (message) {
            is Js5PrefetchGroupMessage -> {
                val response = js5Responses[message.bitpack]
                    ?: throw DecoderException("Invalid group request (${message.archive}:${message.group})")

                if (!prefetchQueue.offer(response))
                    throw IllegalStateException("Filled prefetch queue ($message)")
            }

            is Js5OnDemandGroupMessage -> {
                val response = js5Responses[message.bitpack]
                    ?: throw DecoderException("Invalid group request (${message.archive}:${message.group})")

                if (!onDemandQueue.offer(response))
                    throw IllegalStateException("Filled on-demand queue ($message)")
            }

            is Js5LoggedInMessage -> loggedIn = true
            is Js5LoggedOutMessage -> loggedIn = false

            is Js5DisconnectMessage -> ctx.close()
        }
    }

    fun sendResponse(ctx: ChannelHandlerContext, responseCode: Int) {
        val buf = ctx.alloc().buffer(1, 1)
            .writeByte(responseCode)
        ctx.writeAndFlush(buf, ctx.voidPromise())

        ctx.channel().config().isAutoRead = true
    }

    override fun readComplete(session: Session, ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        if (channel.isWritable) {
            poll(ctx)
        } else {
            channel.config().isAutoRead = false
        }
    }

    override fun writabilityChanged(session: Session, ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        if (channel.isWritable) {
            channel.config().isAutoRead = true
        } else {
            ctx.flush()
            if (!channel.isWritable)
                channel.config().isAutoRead = false
        }
    }

    private fun poll(ctx: ChannelHandlerContext): Boolean {
        var written = false

        val channel = ctx.channel()
        if (!channel.isActive) return false
        while (channel.isWritable) {
            val request = onDemandQueue.poll()
                ?: (if (loggedIn) break else prefetchQueue.poll())
                ?: break

            ctx.write(request.retainedDuplicate(), ctx.voidPromise())
            written = true
        }

        if (written) {
            ctx.flush()
            return true
        }
        return false
    }

    init {
        setDecoder(0, Js5PrefetchGroupMessageDecoder)
        setDecoder(1, Js5OnDemandGroupMessageDecoder)

        setDecoder(2, Js5LoggedInMessageDecoder)
        setDecoder(3, Js5LoggedOutMessageDecoder)

        setDecoder(4, Js5RekeyMessageDecoder)

        setDecoder(5, Js5ConnectedMessageDecoder)
        setDecoder(6, Js5DisconnectMessageDecoder)
    }

    private companion object {
        private const val QUEUE_CAPACITY = 200
    }

}