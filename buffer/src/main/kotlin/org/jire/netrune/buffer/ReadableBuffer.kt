package org.jire.netrune.buffer

interface ReadableBuffer : Buffer {

    fun has(bytes: Int): Boolean

    fun byte(): Int
    fun byteA(): Int
    fun byteC(): Int
    fun byteS(): Int

    fun uByte(): Int
    fun uByteA(): Int
    fun uByteC(): Int
    fun uByteS(): Int

    fun short(): Int
    fun shortA(): Int
    fun shortC(): Int
    fun shortS(): Int

    fun uShort(): Int
    fun uShortA(): Int
    fun uShortC(): Int
    fun uShortS(): Int

    fun shortLe(): Int
    fun shortLeA(): Int
    fun shortLeC(): Int
    fun shortLeS(): Int

    fun uShortLe(): Int
    fun uShortLeA(): Int
    fun uShortLeC(): Int
    fun uShortLeS(): Int

    fun int(): Int
    fun intLe(): Int
    fun intAlt3(): Int
    fun intAlt3Reversed(): Int

    fun uInt(): Long
    fun uIntLe(): Long

    fun varInt(): Int

    fun intSmart(): Int
    fun uIntSmart(): Int

    fun long(): Long
    fun longLe(): Long

    fun string(): String
    fun versionedString(): String

}
