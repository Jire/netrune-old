package org.jire.netrune.buffer.panama

import org.jire.netrune.buffer.ReadableBuffer
import java.lang.foreign.ValueLayout

interface PanamaReadableBuffer : PanamaBuffer, ReadableBuffer {

    override var readerIndex: Int

    override fun getByte(index: Int): Byte = memorySegment.get(ValueLayout.JAVA_BYTE, index.toLong())
    override fun getByteA(index: Int): Byte = (getByte(index) - 128).toByte()
    override fun getByteC(index: Int): Byte = (-getByte(index)).toByte()
    override fun getByteS(index: Int): Byte = (128 - getByte(index)).toByte()

    override fun getUByte(index: Int): Short = (getByte(index).toInt() and 0xFF).toShort()
    override fun getUByteA(index: Int): Short = ((getByte(index).toInt() and 0xFF) - 128).toShort()
    override fun getUByteC(index: Int): Short = (-(getByte(index).toInt() and 0xFF)).toShort()
    override fun getUByteS(index: Int): Short = (128 - (getByte(index).toInt() and 0xFF)).toShort()

    override fun readByte() = getByte(readerIndex++)

    override fun readByteA() = getByteA(readerIndex++)

    override fun readByteC() = getByteC(readerIndex++)

    override fun readByteS() = getByteS(readerIndex++)

}