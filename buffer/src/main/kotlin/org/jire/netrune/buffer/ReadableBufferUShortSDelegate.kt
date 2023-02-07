package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortSDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortS()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
