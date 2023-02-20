package org.jire.netrune.stackgpi

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

@JvmInline
value class MemorySegmentBuf(
    val memorySegment: MemorySegment
) {



    fun byte(index: Long, value: Int) = memorySegment.set(ValueLayout.JAVA_BYTE, index, value.toByte())

    fun byteA(index: Long, value: Int) = byte(index, value + 128)

    fun byteC(index: Long, value: Int) = byte(index, -value)

    fun byteS(index: Long, value: Int) = byte(index, 128 - value)

    fun short(index: Long, value: Int) = memorySegment.set(ValueLayout.JAVA_SHORT, index, value.toShort())

    fun shortA(index: Long, value: Int) = short(index, (value shr 8) or (value + 128))

    fun shortC(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun shortS(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun shortLe(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun shortLeA(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun shortLeC(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun shortLeS(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun int(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun intLe(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun intAlt3(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun intAlt3Reversed(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun varInt(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun intSmart(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun uIntSmart(index: Long, value: Int) {
        TODO("Not yet implemented")
    }

    fun long(value: Long) {
        TODO("Not yet implemented")
    }

    fun longLe(value: Long) {
        TODO("Not yet implemented")
    }

    fun string(value: CharSequence) {
        TODO("Not yet implemented")
    }

    fun versionedString(value: CharSequence) {
        TODO("Not yet implemented")
    }


}