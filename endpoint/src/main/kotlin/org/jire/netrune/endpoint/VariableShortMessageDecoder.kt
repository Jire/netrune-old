package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

abstract class VariableShortMessageDecoder<M : IncomingMessage> : MessageDecoder<M> {

    override fun isLengthReadable(input: ByteBuf) = input.isReadable(2)

    override fun readLength(input: ByteBuf) = input.readUnsignedShort()

}