package org.jire.netrune.codec

import org.jire.netrune.buffer.ReadableBuffer

interface BufferDecoder {

    val input: ReadableBuffer get() = TODO()

    fun output(message: Any) {
        TODO()
    }

    fun decode()

}
