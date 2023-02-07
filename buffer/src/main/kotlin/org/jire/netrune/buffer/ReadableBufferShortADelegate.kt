package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferShortADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readShortA().toInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
