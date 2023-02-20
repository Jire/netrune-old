package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext

open class ConnectService : Service {

    private var length = -1

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        if (!input.isReadable(2)) return false

        length = input.readUnsignedShort()
        return true
    }

    private fun decodeConnectData(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): ConnectData? {
        val version = input.readInt() // 211
        val subVersion = input.readInt() // 1
        val clientType = input.readUnsignedByte().toInt() // 1 = desktop
        val jxType = input.readUnsignedByte().toInt() // 0 = default, 5 = active
        val encryptionType = input.readUnsignedByte().toInt() // 0 = rsa
        val encryptionLength = input.readUnsignedShort()
        val encryptionData = input.readBytes(encryptionLength)

        // TODO: verify connect data

        return ConnectData(version, subVersion, clientType, jxType, encryptionType, encryptionLength, encryptionData)
    }

    protected open fun service(connectData: ConnectData): Service =
        ProofOfWorkService(connectData)

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        if (!input.isReadable(length)) return this

        val connectData = decodeConnectData(ctx, input, out) ?: return this
        return service(connectData)
    }

}