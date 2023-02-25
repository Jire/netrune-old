package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandlerContext

interface Service {

    fun setDecoder(opcode: Int, decoder: MessageDecoder<*>)

    fun getDecoder(opcode: Int): MessageDecoder<*>?

    fun handle(session: Session, ctx: ChannelHandlerContext, message: DecodeMessage)

    fun readComplete(session: Session, ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    fun writabilityChanged(session: Session, ctx: ChannelHandlerContext) {
    }

}