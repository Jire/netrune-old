package org.jire.netrune.endpoint.js5.incoming

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5OnDemandGroupDecoder : FixedMessageDecoder<Js5OnDemandGroup>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5OnDemandGroup {
        val bitpack = input.readUnsignedMedium()
        return Js5OnDemandGroup(bitpack)
    }

}