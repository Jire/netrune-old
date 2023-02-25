package org.jire.netrune.endpoint.js5

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.DecoderException
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.DecodeMessage
import org.jire.netrune.endpoint.Js5Responses
import org.jire.netrune.endpoint.Session

class Js5Service(
    private val js5Responses: Js5Responses
) : AbstractService(7) {

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: DecodeMessage) {
        when (message) {
            is Js5GroupMessage -> {
                val response = js5Responses[message.bitpack]
                    ?: throw DecoderException("Invalid group request (${message.archive}:${message.group})")

                ctx.write(response.retainedDuplicate(), ctx.voidPromise())
            }
        }
    }

    fun sendResponse(ctx: ChannelHandlerContext, responseCode: Int) {
        val buf = ctx.alloc().buffer(1, 1)
            .writeByte(responseCode)
        ctx.write(buf, ctx.voidPromise())
    }

    override fun readComplete(session: Session, ctx: ChannelHandlerContext) {
        ctx.flush()
        ctx.read()
    }

    init {
        setDecoder(0, Js5GroupMessageDecoder)
        setDecoder(1, Js5GroupMessageDecoder)

        setDecoder(2, Js5LoggedInMessageDecoder)
        setDecoder(3, Js5LoggedOutMessageDecoder)

        setDecoder(4, Js5RekeyMessageDecoder)

        setDecoder(5, Js5ConnectedMessageDecoder)
        setDecoder(6, Js5DisconnectMessageDecoder)
    }

}