package org.jire.netrune.buffer

interface WritableBuffer : Buffer {

    fun setByte(index: Int, value: Int)
    fun setShort(index: Int, value: Int)

    fun writeByte(value: Int)
    fun writeByteA(value: Int)
    fun writeByteC(value: Int)
    fun writeByteS(value: Int)

    fun writeShort(value: Int)
    fun writeShortA(value: Int)
    fun writeShortC(value: Int)
    fun writeShortS(value: Int)

    fun writeShortLe(value: Int)
    fun writeShortLeA(value: Int)
    fun writeShortLeC(value: Int)
    fun writeShortLeS(value: Int)

    fun writeInt(value: Int)
    fun writeIntLe(value: Int)
    fun writeIntAlt3(value: Int)
    fun writeIntAlt3Reversed(value: Int)

    fun writeVarInt(value: Int)

    fun writeIntSmart(value: Int)
    fun writeUIntSmart(value: Int)

    fun writeLong(value: Long)
    fun writeLongLe(value: Long)

    fun writeString(value: CharSequence)
    fun writeVersionedString(value: CharSequence)

}
