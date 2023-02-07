package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferLongDelegate : ReadableBufferDelegate {

    var value = Long.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readLong()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
