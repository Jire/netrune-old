package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext

class GameService(
    private val connectData: ConnectData?,
) : Service {

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        val buf = ctx.alloc().buffer()
            .writeByte(2) // login response code, 2 = success

        val lengthWriterIndex = buf.writerIndex()
        buf.writeZero(1) // placeholder for length

        val payloadWriterIndex = buf.writerIndex()

        val authenticated = false
        buf.writeBoolean(authenticated)
        if (authenticated) {
            // TODO: send proper key and progress ISAAC cipher
            buf.writeZero(4)
        } else {
            buf.writeZero(4)
        }

        buf.writeByte(0) // crown
        buf.writeBoolean(false) // is moderator
        buf.writeShort(1) // player index
        buf.writeBoolean(true) // has membership
        buf.writeLong(0) // account hash, used for new character picker in OSRS

        // "actual world server" encoding starts here...

        //buf.writeLong(0) // account registration ID, incrementing. First acc = 0, etc.

        // TODO: rebuild normal packet

        val written = buf.writerIndex() - payloadWriterIndex
        buf.setByte(lengthWriterIndex, written)

        // write rebuild normal content

        return true
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        // TODO: dynamic game packet support
        return this
    }

}