package org.jire.netrune.endpoint.login

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.VariableShortMessageDecoder
import org.openrs2.crypto.StreamCipher

object LoginConnectMessageDecoder : VariableShortMessageDecoder<LoginConnectMessage>() {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): LoginConnectMessage {
        val reconnect = opcode == 18

        val version = input.readInt() // 211
        val subVersion = input.readInt() // 1

        val clientType = input.readUnsignedByte().toInt() // 1 = desktop
        val jxType = input.readUnsignedByte().toInt() // 0 = default, 5 = active

        val encryptionType = input.readUnsignedByte().toInt() // 0 = RSA
        val encryptionLength = input.readUnsignedShort()
        val encryptionData = input.readRetainedSlice(encryptionLength)

        val xteaLength = input.readableBytes()
        val xteaBuf = input.readRetainedSlice(xteaLength)

        return LoginConnectMessage(
            reconnect,
            version, subVersion,
            clientType, jxType,
            encryptionType, encryptionLength, encryptionData,
            xteaLength, xteaBuf
        )
    }

}