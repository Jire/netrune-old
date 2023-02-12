/*
package org.jire.netrune.buffer.panama

@JvmInline
value class UnsignedBufferByte(
    override val buffer: UnsignedBuffer
) : BufferType {

    // write

    operator fun set(index: Long, value: Short) = buffer.set(index, value)

    operator fun set(index: Long, value: Int) = set(index, value.toByte())

    operator fun invoke(value: Short) = set(buffer.writerIndex++, value)

    operator fun invoke(value: Int) = invoke(value.toByte())

    // read

    operator fun get(index: Long): Short = (buffer[index].toInt() and 0xFF).toShort()

    operator fun invoke(): Short = get(buffer.readerIndex++)

    val a get() = BufferByteA(this)

}*/
