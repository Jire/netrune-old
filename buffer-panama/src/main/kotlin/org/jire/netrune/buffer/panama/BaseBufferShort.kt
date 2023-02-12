package org.jire.netrune.buffer.panama

import java.lang.foreign.ValueLayout

@JvmInline
value class BaseBufferShort(
    override val buffer: BaseBuffer
) : BufferShort {

/*    override operator fun set(index: Long, value: Short) {
        return buffer.memorySegment.set(VALUE_LAYOUT, INDEX_HEADER_SIZE + index, value)
    }

    override operator fun get(index: Long) =
        buffer.memorySegment.get(VALUE_LAYOUT, INDEX_HEADER_SIZE + index)*/

    val c get() = BufferShortC(this)
    val a get() = BufferShortA(this)
    val s get() = BufferShortS(this)

    val le get() = BufferShortLe(this)

    private companion object {
        val VALUE_LAYOUT: ValueLayout.OfShort = ValueLayout.JAVA_SHORT.withBitAlignment(Byte.SIZE_BITS.toLong())
    }

}