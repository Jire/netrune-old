package org.jire.netrune.buffer.panama

@JvmInline
value class BufferByteS(
    private val base: BufferByte
) : BufferByte {

    override val buffer: Buffer
        get() = base.buffer

    override fun set(index: Long, value: Byte) {
        base[index] = ByteModifiers.setS(value)
    }

    override fun get(index: Long) = ByteModifiers.getS(base[index])

}