package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShort()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
