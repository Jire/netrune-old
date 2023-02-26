package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import org.openrs2.crypto.StreamCipher

interface MessageDecoder<M : IncomingMessage> {

    fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): M

    fun isLengthReadable(input: ByteBuf): Boolean

    fun readLength(input: ByteBuf): Int

}