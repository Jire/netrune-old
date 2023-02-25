package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5GroupMessageDecoder : FixedMessageDecoder<Js5GroupMessage>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5GroupMessage {
        val bitpack = input.readUnsignedMedium()
        return Js5GroupMessage(bitpack)
    }

}