package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferSkipDelegate(
    private val bytesToSkip: Int
) : ReadableBufferDelegate {

    override fun read(buffer: ReadableBuffer) {
        buffer.skipBytes(bytesToSkip)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = Unit

}
