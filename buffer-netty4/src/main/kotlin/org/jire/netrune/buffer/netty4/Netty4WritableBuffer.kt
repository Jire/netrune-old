package org.jire.netrune.buffer.netty4

import org.jire.netrune.buffer.WritableBuffer
import org.openrs2.buffer.*

interface Netty4WritableBuffer : Netty4Buffer, WritableBuffer {

    override fun byte(value: Int) {
        byteBuf.writeByte(value)
    }

    override fun byteA(value: Int) {
        byteBuf.writeByteA(value)
    }

    override fun byteC(value: Int) {
        byteBuf.writeByteC(value)
    }

    override fun byteS(value: Int) {
        byteBuf.writeByteS(value)
    }

    override fun short(value: Int) {
        byteBuf.writeShort(value)
    }

    override fun shortA(value: Int) {
        byteBuf.writeShortA(value)
    }

    override fun shortC(value: Int) {
        byteBuf.writeShortC(value)
    }

    override fun shortS(value: Int) {
        byteBuf.writeShortS(value)
    }

    override fun shortLe(value: Int) {
        byteBuf.writeShortLE(value)
    }

    override fun shortLeA(value: Int) {
        byteBuf.writeShortLEA(value)
    }

    override fun shortLeC(value: Int) {
        byteBuf.writeShortLEC(value)
    }

    override fun shortLeS(value: Int) {
        byteBuf.writeShortLES(value)
    }

    override fun int(value: Int) {
        byteBuf.writeInt(value)
    }

    override fun intLe(value: Int) {
        byteBuf.writeIntLE(value)
    }

    override fun intAlt3(value: Int) {
        byteBuf.writeIntAlt3(value)
    }

    override fun intAlt3Reversed(value: Int) {
        byteBuf.writeIntAlt3Reverse(value)
    }

    override fun varInt(value: Int) {
        byteBuf.writeVarInt(value)
    }

    override fun intSmart(value: Int) {
        byteBuf.writeIntSmart(value)
    }

    override fun uIntSmart(value: Int) {
        byteBuf.writeUnsignedIntSmart(value)
    }

    override fun long(value: Long) {
        byteBuf.writeLong(value)
    }

    override fun longLe(value: Long) {
        byteBuf.writeLongLE(value)
    }

    override fun string(value: CharSequence) {
        byteBuf.writeString(value)
    }

    override fun versionedString(value: CharSequence) {
        byteBuf.writeVersionedString(value)
    }

}
