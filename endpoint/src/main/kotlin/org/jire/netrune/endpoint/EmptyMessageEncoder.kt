package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import org.openrs2.crypto.StreamCipher

abstract class EmptyMessageEncoder<M : OutgoingMessage>(
    opcode: Int
) : FixedMessageEncoder<M>(opcode, 0) {

    override fun encode(message: M, output: ByteBuf, cipher: StreamCipher) {
    }

}