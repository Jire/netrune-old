package org.jire.netrune.gpikris

import com.google.common.base.Preconditions
import io.netty.buffer.ByteBuf
import io.netty.buffer.DefaultByteBufHolder
import net.openhft.chronicle.core.OS
import kotlin.math.min

class BitBuf(
    val buf: ByteBuf
) : DefaultByteBufHolder(buf), AutoCloseable {

    private var readerIndex: Long = buf.readerIndex().toLong() shl LOG_BITS_PER_BYTE
        private set(value) {
            field = value
            buf.readerIndex((readerIndex shr LOG_BITS_PER_BYTE).toInt())
        }

    private var writerIndex: Long = buf.writerIndex().toLong() shl LOG_BITS_PER_BYTE
        private set(value) {
            field = value
            buf.writerIndex((writerIndex shr LOG_BITS_PER_BYTE).toInt())
        }

    fun getBoolean(index: Long) = getBits(index, 1) != 0

    fun getBit(index: Long) = getBits(index, 1)

    fun getBits(index: Long, len: Int): Int {
        Preconditions.checkArgument(len in 1..BITS_PER_INT)

        if (index < 0 || (index + len) > capacity())
            throw IndexOutOfBoundsException()

        var value = 0

        var remaining = len
        var byteIndex = (index shr LOG_BITS_PER_BYTE).toInt()
        var bitIndex = (index and MASK_BITS_PER_BYTE.toLong()).toInt()
        while (remaining > 0) {
            val n = min(BITS_PER_BYTE - bitIndex, remaining)
            val shift = (BITS_PER_BYTE - (bitIndex + n)) and MASK_BITS_PER_BYTE
            val mask = (1 shl n) - 1
            val v = buf.getUnsignedByte(byteIndex).toInt()
            value = value shl n
            value = value or ((v shr shift) and mask)
            remaining -= n
            byteIndex++
            bitIndex = 0
        }

        return value
    }

    fun readBoolean() = readBits(1) != 0

    fun readBit() = readBits(1)

    fun readBits(len: Int): Int {
        checkReadableBits(len)

        val value = getBits(readerIndex, len)
        readerIndex += len
        return value
    }

    fun skipBits(len: Int): BitBuf {
        checkReadableBits(len)

        readerIndex += len
        return this
    }

    fun setBoolean(index: Long, value: Boolean) = setBits(index, 1, if (value) 1 else 0)

    fun setBit(index: Long, value: Int) = setBits(index, 1, value)

    val m = OS.memory()

    fun setBits(index: Long, len: Int, value: Int): BitBuf {
/*        Preconditions.checkArgument(len in 1..BITS_PER_INT)

        if (index < 0 || (index + len) > capacity())
            throw IndexOutOfBoundsException()*/
        val addr = buf.memoryAddress()

        var remaining = len
        var byteIndex = (index shr LOG_BITS_PER_BYTE).toInt()
        var bitIndex = (index and MASK_BITS_PER_BYTE.toLong()).toInt()
        while (remaining > 0) {
            val n = min(BITS_PER_BYTE - bitIndex, remaining)
            val shift = (BITS_PER_BYTE - (bitIndex + n)) and MASK_BITS_PER_BYTE
            val mask = (1 shl n) - 1
            var v = (m.readByte(addr + byteIndex).toInt() and 0xFF)//buf.getUnsignedByte(byteIndex).toInt()
            v = v and (mask shl shift).inv()
            v = v or (((value shr (remaining - n)) and mask) shl shift)
            m.writeByte(addr + byteIndex, v.toByte())//buf.setByte(byteIndex, v)
            remaining -= n
            byteIndex++
            bitIndex = 0
        }

        return this
    }

    fun writeBoolean(value: Boolean) = writeBits(1, if (value) 1 else 0)

    fun writeBit(value: Int) = writeBits(1, value)

    fun writeBits(len: Int, value: Int): BitBuf {
        ensureWritable(len.toLong())

        setBits(writerIndex, len, value)
        writerIndex += len

        return this
    }

    fun writeZero(len: Int) = writeBits(len, 0)

    private fun checkReadableBits(len: Int) {
        Preconditions.checkArgument(len >= 0)

        if ((readerIndex + len) > writerIndex)
            throw IndexOutOfBoundsException()
    }

    fun ensureWritable(len: Long): BitBuf {
/*        Preconditions.checkArgument(len >= 0)

        if ((writerIndex + len) > maxCapacity())
            throw IndexOutOfBoundsException()*/

        val currentByteIndex = writerIndex shr LOG_BITS_PER_BYTE
        val nextByteIndex = (writerIndex + len + MASK_BITS_PER_BYTE) shr LOG_BITS_PER_BYTE

        buf.ensureWritable((nextByteIndex - currentByteIndex).toInt())
        return this
    }

    fun readableBits() = writerIndex - readerIndex

    fun writableBits() = capacity() - writerIndex

    fun maxWritableBits() = maxCapacity() - writerIndex

    fun capacity() = buf.capacity().toLong() shl LOG_BITS_PER_BYTE

    fun capacity(len: Long): BitBuf {
        buf.capacity((len shr LOG_BITS_PER_BYTE).toInt())
        return this
    }

    fun maxCapacity() = buf.maxCapacity().toLong() shl LOG_BITS_PER_BYTE

    fun isReadable() = readerIndex < writerIndex

    fun isReadable(len: Long): Boolean {
        Preconditions.checkArgument(len >= 0)

        return (readerIndex + len) <= writerIndex
    }

    fun isWritable() = writerIndex < capacity()

    fun isWritable(len: Long): Boolean {
        Preconditions.checkArgument(len >= 0)

        return (writerIndex + len) <= capacity()
    }

    fun readerIndex() = readerIndex

    fun readerIndex(index: Long): BitBuf {
        if (index < 0 || index > writerIndex)
            throw IndexOutOfBoundsException()

        readerIndex = index
        return this
    }

    fun writerIndex() = writerIndex

    fun writerIndex(index: Long): BitBuf {
        if (index < readerIndex || index > capacity())
            throw IndexOutOfBoundsException()

        writerIndex = index
        return this
    }

    fun clear(): BitBuf {
        readerIndex = 0
        writerIndex = 0
        return this
    }

    fun writeBytes(destination: ByteBuf): BitBuf {
        val writer = writerIndex() + 7 shr 3
        for (i in readerIndex() shr 3 until writer) {
            destination.writeByte(destination.getByte(i.toInt()).toInt())
        }
        return clear()
    }

    override fun close() {
        val bits = (((writerIndex + MASK_BITS_PER_BYTE) and MASK_BITS_PER_BYTE.toLong().inv()) - writerIndex).toInt()
        if (bits != 0) writeZero(bits)
        readerIndex = (readerIndex + MASK_BITS_PER_BYTE) and MASK_BITS_PER_BYTE.toLong().inv()
    }

    private companion object {
        private const val LOG_BITS_PER_BYTE = 3
        private const val BITS_PER_BYTE = 1 shl LOG_BITS_PER_BYTE
        private const val MASK_BITS_PER_BYTE = BITS_PER_BYTE - 1
        private const val BITS_PER_INT = 32
    }
}
