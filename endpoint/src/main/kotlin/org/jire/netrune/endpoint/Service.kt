package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext

interface Service {

    fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean = true

    fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service = this

    fun readComplete(ctx: ChannelHandlerContext) {
    }

}