package org.jire.netrune.buffer.panama

import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.MemorySession
import java.lang.foreign.ValueLayout

@JvmInline
value class BaseBuffer(
    override val memorySegment: MemorySegment
) : Buffer {

    constructor(
        memorySession: MemorySession,
        bytesSize: Long
    ) : this(
        memorySession.allocate(
            MemoryLayout.structLayout(
                ValueLayout.JAVA_LONG.withName("writerIndex"),
                ValueLayout.JAVA_LONG.withName("readerIndex"),
                MemoryLayout.sequenceLayout(bytesSize, ValueLayout.JAVA_BYTE)
            )
        )
    )

    override var writerIndex: Long
        get() = memorySegment.get(ValueLayout.JAVA_LONG, WRITER_INDEX_OFFSET)
        set(value) = memorySegment.set(ValueLayout.JAVA_LONG, WRITER_INDEX_OFFSET, value)

    override var readerIndex: Long
        get() = memorySegment.get(ValueLayout.JAVA_LONG, READER_INDEX_OFFSET)
        set(value) = memorySegment.set(ValueLayout.JAVA_LONG, READER_INDEX_OFFSET, value)

    val byte get() = BaseBufferByte(this)
    val short get() = BaseBufferShort(this)

    @PublishedApi
    internal companion object {
        const val WRITER_INDEX_OFFSET = 0L
        const val READER_INDEX_OFFSET = WRITER_INDEX_OFFSET + Long.SIZE_BYTES

        const val INDEX_HEADER_SIZE = Long.SIZE_BYTES + Long.SIZE_BYTES
    }

}

fun MemorySession.buffer(bytesSize: Long) = BaseBuffer(this, bytesSize)