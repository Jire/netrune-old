package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortA()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
