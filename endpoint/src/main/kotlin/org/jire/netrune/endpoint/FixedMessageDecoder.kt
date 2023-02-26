package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

abstract class FixedMessageDecoder<M : IncomingMessage>(
    protected val length: Int
) : MessageDecoder<M> {

    override fun isLengthReadable(input: ByteBuf) = true

    override fun readLength(input: ByteBuf) = length

}