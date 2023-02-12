package org.jire.netrune.buffer.panama

@JvmInline
value class BufferByteC(
    private val base: BufferByte
) : BufferByte {

    override val buffer: Buffer
        get() = base.buffer

    override fun set(index: Long, value: Byte) {
        base[index] = ByteModifiers.setC(value)
    }

    override fun get(index: Long) = ByteModifiers.getC(base[index])

}