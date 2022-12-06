package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUByteADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.uByteA()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
