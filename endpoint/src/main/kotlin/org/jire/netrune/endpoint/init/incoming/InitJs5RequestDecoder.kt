package org.jire.netrune.endpoint.init.incoming

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object InitJs5RequestDecoder : FixedMessageDecoder<InitJs5>(4) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): InitJs5 {
        val version = input.readInt()
        return InitJs5(version)
    }

}