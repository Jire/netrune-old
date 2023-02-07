package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferShortLeSDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readShortLeS().toInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
