package org.jire.netrune.buffer.netty4

import org.jire.netrune.buffer.ReadableBuffer
import org.openrs2.buffer.*

interface Netty4ReadableBuffer : Netty4Buffer, ReadableBuffer {

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
