package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferByteADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.byteA()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
