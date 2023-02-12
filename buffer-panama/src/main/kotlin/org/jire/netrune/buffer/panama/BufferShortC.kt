package org.jire.netrune.buffer.panama

@JvmInline
value class BufferShortC(
    private val base: BufferShort
) : BufferShort by base {

    override fun set2(value: Int) = ByteModifiers.setC(value) and 0xFF
    override fun get2(value: Int) = ByteModifiers.getC(value) and 0xFF

}