package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import org.openrs2.crypto.StreamCipher

interface MessageEncoder<M : OutgoingMessage> {

    val opcode: Int

    fun encode(message: M, output: ByteBuf, cipher: StreamCipher)

    fun writeLengthPlaceholder(output: ByteBuf)

    fun setLength(output: ByteBuf, lengthWriterIndex: Int, length: Int)

}