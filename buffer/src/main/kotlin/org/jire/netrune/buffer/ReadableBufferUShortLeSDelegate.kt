package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortLeSDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortLeS()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
