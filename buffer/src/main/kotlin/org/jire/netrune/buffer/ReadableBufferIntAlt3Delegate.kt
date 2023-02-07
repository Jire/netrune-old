package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferIntAlt3Delegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readIntAlt3()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
