package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object Js5OnDemandGroupMessageDecoder : FixedMessageDecoder<Js5OnDemandGroupMessage>(3) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): Js5OnDemandGroupMessage {
        val bitpack = input.readUnsignedMedium()
        return Js5OnDemandGroupMessage(bitpack)
    }

}