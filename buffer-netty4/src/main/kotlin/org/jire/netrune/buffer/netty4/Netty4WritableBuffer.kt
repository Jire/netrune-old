package org.jire.netrune.buffer.netty4

import org.jire.netrune.buffer.WritableBuffer
import org.openrs2.buffer.*

interface Netty4WritableBuffer : Netty4Buffer, WritableBuffer {

    override fun writeByte(value: Int) {
        byteBuf.writeByte(value)
    }

    override fun writeByteA(value: Int) {
        byteBuf.writeByteA(value)
    }

    override fun writeByteC(value: Int) {
        byteBuf.writeByteC(value)
    }

    override fun writeByteS(value: Int) {
        byteBuf.writeByteS(value)
    }

    override fun writeShort(value: Int) {
        byteBuf.writeShort(value)
    }

    override fun writeShortA(value: Int) {
        byteBuf.writeShortA(value)
    }

    override fun writeShortC(value: Int) {
        byteBuf.writeShortC(value)
    }

    override fun writeShortS(value: Int) {
        byteBuf.writeShortS(value)
    }

    override fun writeShortLe(value: Int) {
        byteBuf.writeShortLE(value)
    }

    override fun writeShortLeA(value: Int) {
        byteBuf.writeShortLEA(value)
    }

    override fun writeShortLeC(value: Int) {
        byteBuf.writeShortLEC(value)
    }

    override fun writeShortLeS(value: Int) {
        byteBuf.writeShortLES(value)
    }

    override fun writeInt(value: Int) {
        byteBuf.writeInt(value)
    }

    override fun writeIntLe(value: Int) {
        byteBuf.writeIntLE(value)
    }

    override fun writeIntAlt3(value: Int) {
        byteBuf.writeIntAlt3(value)
    }

    override fun writeIntAlt3Reversed(value: Int) {
        byteBuf.writeIntAlt3Reverse(value)
    }

    override fun writeVarInt(value: Int) {
        byteBuf.writeVarInt(value)
    }

    override fun writeIntSmart(value: Int) {
        byteBuf.writeIntSmart(value)
    }

    override fun writeUIntSmart(value: Int) {
        byteBuf.writeUnsignedIntSmart(value)
    }

    override fun writeLong(value: Long) {
        byteBuf.writeLong(value)
    }

    override fun writeLongLe(value: Long) {
        byteBuf.writeLongLE(value)
    }

    override fun writeString(value: CharSequence) {
        byteBuf.writeString(value)
    }

    override fun writeVersionedString(value: CharSequence) {
        byteBuf.writeVersionedString(value)
    }

}
