package org.jire.netrune.buffer.netty4

import org.jire.netrune.buffer.ReadableBuffer
import org.openrs2.buffer.*

interface Netty4ReadableBuffer : Netty4Buffer, ReadableBuffer {

    override fun has(bytes: Int) = byteBuf.isReadable(bytes)

    override fun byte(): Int = byteBuf.readByte().toInt()
    override fun byteA(): Int = byteBuf.readByteA().toInt()
    override fun byteC(): Int = byteBuf.readByteC().toInt()
    override fun byteS(): Int = byteBuf.readByteS().toInt()

    override fun uByte(): Int = byteBuf.readUnsignedByte().toInt()
    override fun uByteA(): Int = byteBuf.readUnsignedByteA().toInt()
    override fun uByteC(): Int = byteBuf.readUnsignedByteC().toInt()
    override fun uByteS(): Int = byteBuf.readUnsignedByteS().toInt()

    override fun short(): Int = byteBuf.readShort().toInt()
    override fun shortA(): Int = byteBuf.readShortA().toInt()
    override fun shortC(): Int = byteBuf.readShortC().toInt()
    override fun shortS(): Int = byteBuf.readShortS().toInt()

    override fun uShort(): Int = byteBuf.readUnsignedShort()
    override fun uShortA(): Int = byteBuf.readUnsignedShortA()
    override fun uShortC(): Int = byteBuf.readUnsignedShortC()
    override fun uShortS(): Int = byteBuf.readUnsignedShortS()

    override fun shortLe(): Int = byteBuf.readShortLE().toInt()
    override fun shortLeA(): Int = byteBuf.readShortLEA().toInt()
    override fun shortLeC(): Int = byteBuf.readShortLEC().toInt()
    override fun shortLeS(): Int = byteBuf.readShortLES().toInt()

    override fun uShortLe(): Int = byteBuf.readUnsignedShortLE()
    override fun uShortLeA(): Int = byteBuf.readUnsignedShortLEA()
    override fun uShortLeC(): Int = byteBuf.readUnsignedShortLEC()
    override fun uShortLeS(): Int = byteBuf.readUnsignedShortLES()

    override fun int(): Int = byteBuf.readInt()
    override fun intLe(): Int = byteBuf.readIntLE()
    override fun intAlt3(): Int = byteBuf.readIntAlt3()
    override fun intAlt3Reversed(): Int = byteBuf.readIntAlt3Reverse()

    override fun uInt(): Long = byteBuf.readUnsignedInt()
    override fun uIntLe(): Long = byteBuf.readUnsignedIntLE()

    override fun varInt(): Int = byteBuf.readVarInt()
    override fun intSmart(): Int = byteBuf.readIntSmart()
    override fun uIntSmart(): Int = byteBuf.readUnsignedIntSmart()

    override fun long(): Long = byteBuf.readLong()
    override fun longLe(): Long = byteBuf.readLongLE()

    override fun string(): String = byteBuf.readString()
    override fun versionedString(): String = byteBuf.readVersionedString()

}
