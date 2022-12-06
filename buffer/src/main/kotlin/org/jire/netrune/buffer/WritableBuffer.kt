package org.jire.netrune.buffer

interface WritableBuffer : Buffer {

    fun byte(value: Int)
    fun byteA(value: Int)
    fun byteC(value: Int)
    fun byteS(value: Int)

    fun short(value: Int)
    fun shortA(value: Int)
    fun shortC(value: Int)
    fun shortS(value: Int)

    fun shortLe(value: Int)
    fun shortLeA(value: Int)
    fun shortLeC(value: Int)
    fun shortLeS(value: Int)

    fun int(value: Int)
    fun intLe(value: Int)
    fun intAlt3(value: Int)
    fun intAlt3Reversed(value: Int)

    fun varInt(value: Int)

    fun intSmart(value: Int)
    fun uIntSmart(value: Int)

    fun long(value: Long)
    fun longLe(value: Long)

    fun string(value: CharSequence)
    fun versionedString(value: CharSequence)

}
