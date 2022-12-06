package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferIntDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.int()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
