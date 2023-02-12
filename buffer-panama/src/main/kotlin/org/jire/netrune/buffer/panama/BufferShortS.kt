package org.jire.netrune.buffer.panama

@JvmInline
value class BufferShortS(
    private val base: BufferShort
) : BufferShort by base {

    override fun set2(value: Int) = ByteModifiers.setS(value) and 0xFF
    override fun get2(value: Int) = ByteModifiers.getS(value) and 0xFF

}