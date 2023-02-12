package org.jire.netrune.buffer.netty4

import org.jire.netrune.buffer.ReadableBuffer
import org.openrs2.buffer.*

interface Netty4ReadableBuffer : Netty4Buffer, ReadableBuffer {

    override fun getByte(index: Int): Byte = byteBuf.getByte(index)
    override fun getByteA(index: Int): Byte = byteBuf.getByteA(index)
    override fun getByteC(index: Int): Byte = byteBuf.getByteC(index)
    override fun getByteS(index: Int): Byte = byteBuf.getByteS(index)

    override fun getUByte(index: Int): Short = byteBuf.getUnsignedByte(index)
    override fun getUByteA(index: Int): Short = byteBuf.getUnsignedByteA(index)
    override fun getUByteC(index: Int): Short = byteBuf.getUnsignedByteC(index)
    override fun getUByteS(index: Int): Short = byteBuf.getUnsignedByteS(index)

    override fun getShort(index: Int): Short = byteBuf.getShort(index)
    override fun getShortA(index: Int): Short = byteBuf.getShortA(index)
    override fun getShortC(index: Int): Short = byteBuf.getShortC(index)
    override fun getShortS(index: Int): Short = byteBuf.getShortS(index)

    override fun getUShort(index: Int): Int = byteBuf.getUnsignedShort(index)
    override fun getUShortA(index: Int): Int = byteBuf.getUnsignedShortA(index)
    override fun getUShortC(index: Int): Int = byteBuf.getUnsignedShortC(index)
    override fun getUShortS(index: Int): Int = byteBuf.getUnsignedShortS(index)

    override fun getShortLe(index: Int): Short = byteBuf.getShortLE(index)
    override fun getShortLeA(index: Int): Short = byteBuf.getShortLEA(index)
    override fun getShortLeC(index: Int): Short = byteBuf.getShortLEC(index)
    override fun getShortLeS(index: Int): Short = byteBuf.getShortLES(index)

    override fun getUShortLe(index: Int): Int = byteBuf.getUnsignedShortLE(index)
    override fun getUShortLeA(index: Int): Int = byteBuf.getUnsignedShortLEA(index)
    override fun getUShortLeC(index: Int): Int = byteBuf.getUnsignedShortLEC(index)
    override fun getUShortLeS(index: Int): Int = byteBuf.getUnsignedShortLES(index)

    override fun getInt(index: Int): Int = byteBuf.getInt(index)
    override fun getIntLe(index: Int): Int = byteBuf.getIntLE(index)
    override fun getIntAlt3(index: Int): Int = byteBuf.getIntAlt3(index)
    override fun getIntAlt3Reversed(index: Int): Int = byteBuf.getIntAlt3Reverse(index)

    override fun getUInt(index: Int): Long = byteBuf.getUnsignedInt(index)
    override fun getUIntLe(index: Int): Long = byteBuf.getUnsignedIntLE(index)

    override fun getLong(index: Int): Long = byteBuf.getLong(index)
    override fun getLongLe(index: Int): Long = byteBuf.getLongLE(index)

    override fun readByte(): Byte = byteBuf.readByte()
    override fun readByteA(): Byte = byteBuf.readByteA()
    override fun readByteC(): Byte = byteBuf.readByteC()
    override fun readByteS(): Byte = byteBuf.readByteS()

    override fun readUByte(): Short = byteBuf.readUnsignedByte()
    override fun readUByteA(): Short = byteBuf.readUnsignedByteA()
    override fun readUByteC(): Short = byteBuf.readUnsignedByteC()
    override fun readUByteS(): Short = byteBuf.readUnsignedByteS()

    override fun readShort(): Short = byteBuf.readShort()
    override fun readShortA(): Short = byteBuf.readShortA()
    override fun readShortC(): Short = byteBuf.readShortC()
    override fun readShortS(): Short = byteBuf.readShortS()

    override fun readUShort(): Int = byteBuf.readUnsignedShort()
    override fun readUShortA(): Int = byteBuf.readUnsignedShortA()
    override fun readUShortC(): Int = byteBuf.readUnsignedShortC()
    override fun readUShortS(): Int = byteBuf.readUnsignedShortS()

    override fun readShortLe(): Short = byteBuf.readShortLE()
    override fun readShortLeA(): Short = byteBuf.readShortLEA()
    override fun readShortLeC(): Short = byteBuf.readShortLEC()
    override fun readShortLeS(): Short = byteBuf.readShortLES()

    override fun readUShortLe(): Int = byteBuf.readUnsignedShortLE()
    override fun readUShortLeA(): Int = byteBuf.readUnsignedShortLEA()
    override fun readUShortLeC(): Int = byteBuf.readUnsignedShortLEC()
    override fun readUShortLeS(): Int = byteBuf.readUnsignedShortLES()

    override fun readInt(): Int = byteBuf.readInt()
    override fun readIntLe(): Int = byteBuf.readIntLE()
    override fun readIntAlt3(): Int = byteBuf.readIntAlt3()
    override fun readIntAlt3Reversed(): Int = byteBuf.readIntAlt3Reverse()

    override fun readUInt(): Long = byteBuf.readUnsignedInt()
    override fun readUIntLe(): Long = byteBuf.readUnsignedIntLE()

    override fun readVarInt(): Int = byteBuf.readVarInt()
    override fun readIntSmart(): Int = byteBuf.readIntSmart()
    override fun readUIntSmart(): Int = byteBuf.readUnsignedIntSmart()

    override fun readLong(): Long = byteBuf.readLong()
    override fun readLongLe(): Long = byteBuf.readLongLE()

    override fun readString(): String = byteBuf.readString()
    override fun readVersionedString(): String = byteBuf.readVersionedString()

    override fun skipBytes(length: Int) {
        byteBuf.skipBytes(length)
    }

}
