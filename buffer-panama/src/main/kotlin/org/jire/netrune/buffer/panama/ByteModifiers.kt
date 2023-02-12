package org.jire.netrune.buffer.panama

@Suppress("NOTHING_TO_INLINE")
object ByteModifiers {

    @JvmStatic
    inline fun setC(value: Int) = -value

    @JvmStatic
    inline fun setC(value: Byte) = setC(value.toInt())

    @JvmStatic
    inline fun getC(value: Int) = -value

    @JvmStatic
    inline fun getC(value: Byte) = getC(value.toInt()).toByte()

    @JvmStatic
    inline fun setA(value: Int) = value + 128

    @JvmStatic
    inline fun setA(value: Byte) = setA(value.toInt())

    @JvmStatic
    inline fun setA(value: Short) = setA(value.toInt())

    @JvmStatic
    inline fun getA(value: Int) = value - 128

    @JvmStatic
    inline fun getA(value: Byte) = getA(value.toInt()).toByte()

    @JvmStatic
    inline fun setS(value: Int) = value - 128

    @JvmStatic
    inline fun setS(value: Byte) = setS(value.toInt())

    @JvmStatic
    inline fun getS(value: Int) = 128 - value

    @JvmStatic
    inline fun getS(value: Byte) = getS(value.toInt()).toByte()

}