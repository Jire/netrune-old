package org.jire.netrune.buffer

interface ReadableBuffer : Buffer {

    fun getByte(index: Int): Byte
    fun getByteA(index: Int): Byte
    fun getByteC(index: Int): Byte
    fun getByteS(index: Int): Byte

    fun getUByte(index: Int): Short
    fun getUByteA(index: Int): Short
    fun getUByteC(index: Int): Short
    fun getUByteS(index: Int): Short

    fun getShort(index: Int): Short
    fun getShortA(index: Int): Short
    fun getShortC(index: Int): Short
    fun getShortS(index: Int): Short

    fun getUShort(index: Int): Int
    fun getUShortA(index: Int): Int
    fun getUShortC(index: Int): Int
    fun getUShortS(index: Int): Int

    fun getShortLe(index: Int): Short
    fun getShortLeA(index: Int): Short
    fun getShortLeC(index: Int): Short
    fun getShortLeS(index: Int): Short

    fun getUShortLe(index: Int): Int
    fun getUShortLeA(index: Int): Int
    fun getUShortLeC(index: Int): Int
    fun getUShortLeS(index: Int): Int

    fun getInt(index: Int): Int
    fun getIntLe(index: Int): Int
    fun getIntAlt3(index: Int): Int
    fun getIntAlt3Reversed(index: Int): Int

    fun getUInt(index: Int): Long
    fun getUIntLe(index: Int): Long

    fun getLong(index: Int): Long
    fun getLongLe(index: Int): Long

    fun readByte(): Byte
    fun readByteA(): Byte
    fun readByteC(): Byte
    fun readByteS(): Byte

    fun readUByte(): Short
    fun readUByteA(): Short
    fun readUByteC(): Short
    fun readUByteS(): Short

    fun readShort(): Short
    fun readShortA(): Short
    fun readShortC(): Short
    fun readShortS(): Short

    fun readUShort(): Int
    fun readUShortA(): Int
    fun readUShortC(): Int
    fun readUShortS(): Int

    fun readShortLe(): Short
    fun readShortLeA(): Short
    fun readShortLeC(): Short
    fun readShortLeS(): Short

    fun readUShortLe(): Int
    fun readUShortLeA(): Int
    fun readUShortLeC(): Int
    fun readUShortLeS(): Int

    fun readInt(): Int
    fun readIntLe(): Int
    fun readIntAlt3(): Int
    fun readIntAlt3Reversed(): Int

    fun readUInt(): Long
    fun readUIntLe(): Long

    fun readVarInt(): Int

    fun readIntSmart(): Int
    fun readUIntSmart(): Int

    fun readLong(): Long
    fun readLongLe(): Long

    fun readString(): String
    fun readVersionedString(): String

    fun skipBytes(length: Int)

}
