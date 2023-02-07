package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferIntAlt3ReversedDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readIntAlt3Reversed()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
