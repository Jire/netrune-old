package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

abstract class VariableShortMessageEncoder<M : OutgoingMessage>(
    opcode: Int
) : AbstractMessageEncoder<M>(opcode) {

    override fun writeLengthPlaceholder(output: ByteBuf) {
        output.writeZero(2)
    }

    override fun setLength(output: ByteBuf, lengthWriterIndex: Int, length: Int) {
        require(length <= 0xFFFF)

        output.setShort(lengthWriterIndex, length)
    }

}