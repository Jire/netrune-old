package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUIntDelegate : ReadableBufferDelegate {

    var value = Long.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
