package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortCDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortC()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
