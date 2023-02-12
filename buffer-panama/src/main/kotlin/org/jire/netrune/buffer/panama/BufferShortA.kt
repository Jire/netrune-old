package org.jire.netrune.buffer.panama

@JvmInline
value class BufferShortA(
    private val base: BufferShort
) : BufferShort by base {

    override fun set2(value: Int): Int {
        val v = ByteModifiers.setA(value) and 0xFF
        println(v)
        return v
    }
    override fun get2(value: Int) = ByteModifiers.getA(value) and 0xFF

}