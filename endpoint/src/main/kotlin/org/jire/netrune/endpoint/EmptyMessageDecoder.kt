package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import org.openrs2.crypto.StreamCipher

abstract class EmptyMessageDecoder<M : IncomingMessage>(
    private val message: M
) : FixedMessageDecoder<M>(0) {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher) = message

}