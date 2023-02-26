package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

abstract class VariableByteMessageEncoder<M : OutgoingMessage>(
    opcode: Int
) : AbstractMessageEncoder<M>(opcode) {

    override fun writeLengthPlaceholder(output: ByteBuf) {
        output.writeZero(1)
    }

    override fun setLength(output: ByteBuf, lengthWriterIndex: Int, length: Int) {
        require(length <= 0xFF)

        output.setByte(lengthWriterIndex, length)
    }

}