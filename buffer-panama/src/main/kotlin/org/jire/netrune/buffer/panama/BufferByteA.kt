package org.jire.netrune.buffer.panama

@JvmInline
value class BufferByteA(
    private val base: BaseBufferByte
) : BufferByte {

    override val buffer: BaseBuffer
        get() = base.buffer

    override fun set(index: Long, value: Byte) {
        base[index] = ByteModifiers.setA(value)
    }

    override fun get(index: Long) = ByteModifiers.getA(base[index])

}