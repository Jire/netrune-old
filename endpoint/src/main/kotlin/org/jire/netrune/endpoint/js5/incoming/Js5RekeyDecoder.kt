package org.jire.netrune.endpoint.js5.incoming

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5RekeyDecoder : FixedMessageDecoder<Js5Rekey>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5Rekey {
        val key = input.readUnsignedByte().toInt()
        input.skipBytes(2)
        return Js5Rekey(key)
    }

}