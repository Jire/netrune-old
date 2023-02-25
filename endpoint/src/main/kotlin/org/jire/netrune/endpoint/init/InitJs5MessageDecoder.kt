package org.jire.netrune.endpoint.init

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageDecoder
import org.openrs2.crypto.StreamCipher

object InitJs5MessageDecoder : FixedMessageDecoder<InitJs5Message>(4) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): InitJs5Message {
        val version = input.readInt()
        return InitJs5Message(version)
    }

}