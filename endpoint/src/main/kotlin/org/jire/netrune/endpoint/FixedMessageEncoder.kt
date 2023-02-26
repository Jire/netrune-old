package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

abstract class FixedMessageEncoder<M : OutgoingMessage>(
    opcode: Int,
    private val length: Int
) : AbstractMessageEncoder<M>(opcode) {

    override fun writeLengthPlaceholder(output: ByteBuf) {
    }

    override fun setLength(output: ByteBuf, lengthWriterIndex: Int, length: Int) {
        require(length == this.length)
    }

}