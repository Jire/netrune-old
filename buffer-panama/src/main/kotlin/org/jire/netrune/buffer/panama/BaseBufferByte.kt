package org.jire.netrune.buffer.panama

import org.jire.netrune.buffer.panama.BaseBuffer.Companion.INDEX_HEADER_SIZE
import java.lang.foreign.ValueLayout

@JvmInline
value class BaseBufferByte(
    override val buffer: BaseBuffer
) : BufferByte {

    override operator fun set(index: Long, value: Byte) {
        return buffer.memorySegment.set(ValueLayout.JAVA_BYTE, INDEX_HEADER_SIZE + index, value)
    }

    override operator fun get(index: Long): Byte =
        buffer.memorySegment.get(ValueLayout.JAVA_BYTE, INDEX_HEADER_SIZE + index)

    val c get() = BufferByteC(this)
    val a get() = BufferByteA(this)
    val s get() = BufferByteS(this)

}