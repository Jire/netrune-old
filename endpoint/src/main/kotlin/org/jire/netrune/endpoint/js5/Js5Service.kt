package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.DecoderException
import org.jctools.queues.MessagePassingQueue
import org.jctools.queues.SpscArrayQueue
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.IncomingMessage
import org.jire.netrune.endpoint.Session
import org.jire.netrune.endpoint.js5.incoming.*

class Js5Service(
    private val js5GroupRepository: Js5GroupRepository
) : AbstractService(7) {

    private val prefetchQueue: MessagePassingQueue<ByteBuf> = SpscArrayQueue(QUEUE_CAPACITY)
    private val onDemandQueue: MessagePassingQueue<ByteBuf> = SpscArrayQueue(QUEUE_CAPACITY)

    private var loggedIn = false

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: IncomingMessage) {
        when (message) {
            is Js5PrefetchGroup -> {
                val response = js5GroupRepository[message.bitpack]
                    ?: throw DecoderException("Invalid group request (${message.archive}:${message.group})")

                if (!prefetchQueue.offer(response))
                    throw IllegalStateException("Filled prefetch queue ($message)")
            }

            is Js5OnDemandGroup -> {
                val response = js5GroupRepository[message.bitpack]
                    ?: throw DecoderException("Invalid group request (${message.archive}:${message.group})")

                if (!onDemandQueue.offer(response))
                    throw IllegalStateException("Filled on-demand queue ($message)")
            }

            is Js5LoggedIn -> loggedIn = true
            is Js5LoggedOut -> loggedIn = false

            is Js5Disconnect -> ctx.close()
        }
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
        setDecoder(0, Js5PrefetchGroupDecoder)
        setDecoder(1, Js5OnDemandGroupDecoder)

        setDecoder(2, Js5LoggedInDecoder)
        setDecoder(3, Js5LoggedOutDecoder)

        setDecoder(4, Js5RekeyDecoder)

        setDecoder(5, Js5ConnectedDecoder)
        setDecoder(6, Js5DisconnectDecoder)
    }

    private companion object {
        private const val QUEUE_CAPACITY = 200
    }

}