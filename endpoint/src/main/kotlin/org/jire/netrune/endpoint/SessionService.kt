package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext

object SessionService : Service {

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        val bufSize = 1 + 8
        val buf = ctx.alloc().buffer(bufSize, bufSize)
            .writeByte(0) // response opcode
            .writeLong(0) // session key
        ctx.writeAndFlush(buf, ctx.voidPromise())
        ctx.read()
        return false
    }

}