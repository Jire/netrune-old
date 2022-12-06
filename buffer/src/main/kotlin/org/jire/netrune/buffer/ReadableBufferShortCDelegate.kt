package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferShortCDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.shortC()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
