package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferLongLeDelegate : ReadableBufferDelegate {

    var value = Long.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readLongLe()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
