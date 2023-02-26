package org.jire.netrune.endpoint.js5.incoming

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5PrefetchGroupDecoder : FixedMessageDecoder<Js5PrefetchGroup>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5PrefetchGroup {
        val bitpack = input.readUnsignedMedium()
        return Js5PrefetchGroup(bitpack)
    }

}