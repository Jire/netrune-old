package org.jire.netrune.buffer.panama

import java.lang.foreign.ValueLayout

interface BufferShort : BufferType {

    fun set(index: Long, byte1: Byte, byte2: Byte) {
        val buffer = buffer
        buffer[index] = byte1
        buffer[index + 1] = byte2
    }

    fun set1(value: Int) = ((value shr 8) and 0xFF) shl 8
    fun set2(value: Int) = value and 0xFF

    operator fun set(index: Long, value: Short) = set(index, value.toInt())

    operator fun set(index: Long, value: Int) = set(
        index,
        set1(value).toByte(),
        set2(value).toByte()
    )

    operator fun invoke(value: Short) {
        val buffer = buffer
        val writerIndex = buffer.writerIndex
        set(writerIndex, value)
        buffer.writerIndex = writerIndex + Short.SIZE_BYTES
    }

    operator fun invoke(value: Int) = invoke(value.toShort())

    fun get1(value: Int) = ((value ushr 8) and 0xFF) shl 8
    fun get2(value: Int) = value and 0xFF

    operator fun get(index: Long): Short {
        val value = buffer.memorySegment.get(ValueLayout.JAVA_SHORT, index).toInt()
        val byte1 = get1(value)
        val byte2 = get2(value)
        return (byte1 or byte2).toShort()
    }

    operator fun invoke(): Short {
        val buffer = buffer
        val readerIndex = buffer.readerIndex
        val value = get(readerIndex)
        buffer.readerIndex = readerIndex + Short.SIZE_BYTES
        return value
    }

}