package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext

class ProofOfWorkService(
    private val connectData: ConnectData?
) : Service {

    private var length = -1

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        if (!input.isReadable(2)) return false

        length = input.readUnsignedShort()
        return true
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        if (!input.isReadable(length)) return this

        val nonce = input.readLong()
        // TODO: verify nonce

        return GameService(connectData)
    }

}