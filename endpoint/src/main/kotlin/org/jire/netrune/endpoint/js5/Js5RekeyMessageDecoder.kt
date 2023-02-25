package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5RekeyMessageDecoder : FixedMessageDecoder<Js5RekeyMessage>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5RekeyMessage {
        val key = input.readUnsignedByte().toInt()
        input.skipBytes(2)
        return Js5RekeyMessage(key)
    }

}