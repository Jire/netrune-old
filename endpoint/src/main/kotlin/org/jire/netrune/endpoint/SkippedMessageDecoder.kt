package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import org.openrs2.crypto.StreamCipher

abstract class SkippedMessageDecoder<M : IncomingMessage>(
    length: Int,
    private val message: M
) : FixedMessageDecoder<M>(length) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): M {
        input.skipBytes(length)
        return message
    }

}