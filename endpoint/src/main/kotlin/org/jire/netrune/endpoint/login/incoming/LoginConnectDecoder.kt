package org.jire.netrune.endpoint.login.incoming

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.VariableShortMessageDecoder
import org.openrs2.crypto.StreamCipher

object LoginConnectDecoder : VariableShortMessageDecoder<LoginConnect>() {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): LoginConnect {
        val reconnect = opcode == 18

        val buildMajor = input.readInt()
        val buildMinor = input.readInt()

        val clientType = input.readUnsignedByte().toInt()
        val platformType = input.readUnsignedByte().toInt()

        val secureBlockType = input.readUnsignedByte().toInt()
        val secureBlockLength = input.readUnsignedShort()
        val secureBlockData = input.readRetainedSlice(secureBlockLength)

        val xteaBlockLength = input.readableBytes()
        val xteaBlockData = input.readRetainedSlice(xteaBlockLength)

        return LoginConnect(
            reconnect,
            buildMajor, buildMinor,
            clientType, platformType,
            secureBlockType, secureBlockLength, secureBlockData,
            xteaBlockLength, xteaBlockData
        )
    }

}