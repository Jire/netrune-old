package org.jire.netrune.buffer.panama

interface BufferByte : BufferType {

    operator fun set(index: Long, value: Byte)

    operator fun set(index: Long, value: Int) = set(index, value.toByte())

    operator fun invoke(value: Byte) = set(buffer.writerIndex++, value)

    operator fun invoke(value: Int) = invoke(value.toByte())

    operator fun get(index: Long): Byte

    operator fun invoke(): Byte = get(buffer.readerIndex++)

}