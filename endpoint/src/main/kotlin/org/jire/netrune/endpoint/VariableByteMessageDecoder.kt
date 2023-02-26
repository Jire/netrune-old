package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

abstract class VariableByteMessageDecoder<M : IncomingMessage> : MessageDecoder<M> {

    override fun isLengthReadable(input: ByteBuf) = input.isReadable

    override fun readLength(input: ByteBuf) = input.readUnsignedByte().toInt()

}