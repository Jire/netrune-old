package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUIntLeDelegate : ReadableBufferDelegate {

    var value = Long.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUIntLe()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
