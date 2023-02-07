package org.jire.netrune.buffer.panama

import org.jire.netrune.buffer.Buffer
import java.lang.foreign.MemorySegment

interface PanamaBuffer : Buffer {

    val memorySegment: MemorySegment

    val readerIndex: Int
    val writerIndex: Int

    override fun has(bytes: Int) = writerIndex - readerIndex >= bytes

    override fun close() {
        // encompassing memory session takes care of releasing the segment
    }

}