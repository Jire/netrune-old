package org.jire.netrune.buffer.panama

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

interface Buffer {

    val memorySegment: MemorySegment

    var writerIndex: Long
    var readerIndex: Long

    val remaining: Long
        get() = writerIndex - readerIndex

    fun hasRemaining(bytes: Long) = remaining >= bytes

    fun hasRemaining() = hasRemaining(1)

    fun clear() {
        writerIndex = 0
        readerIndex = 0
    }

    operator fun get(index: Long): Byte = memorySegment.get(ValueLayout.JAVA_BYTE, index)

    operator fun set(index: Long, value: Byte) {
        memorySegment.set(ValueLayout.JAVA_BYTE, index, value)
    }

}