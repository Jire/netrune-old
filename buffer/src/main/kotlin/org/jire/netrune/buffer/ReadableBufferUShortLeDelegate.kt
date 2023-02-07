package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortLeDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortLe()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
