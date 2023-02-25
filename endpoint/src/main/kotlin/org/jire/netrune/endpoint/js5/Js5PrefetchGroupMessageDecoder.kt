package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5PrefetchGroupMessageDecoder : FixedMessageDecoder<Js5PrefetchGroupMessage>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5PrefetchGroupMessage {
        val bitpack = input.readUnsignedMedium()
        return Js5PrefetchGroupMessage(bitpack)
    }

}