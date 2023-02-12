package org.jire.netrune.buffer.panama

@JvmInline
value class BufferShortLe(
    private val base: BufferShort
) : BufferShort by base {

    override fun set(index: Long, byte1: Byte, byte2: Byte) {
        val buffer = buffer
        buffer[index] = byte2
        buffer[index + 1] = byte1
    }

    override fun get1(value: Int) = base.get2(value)
    override fun get2(value: Int) = base.get1(value)

    val c get() = BufferShortC(this)
    val a get() = BufferShortA(this)
    val s get() = BufferShortS(this)

}