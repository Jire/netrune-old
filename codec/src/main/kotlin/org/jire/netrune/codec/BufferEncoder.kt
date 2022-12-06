package org.jire.netrune.codec

import org.jire.netrune.buffer.WritableBuffer

interface BufferEncoder<INPUT> {

    val input: INPUT

    val output: WritableBuffer

    fun encode()

}
