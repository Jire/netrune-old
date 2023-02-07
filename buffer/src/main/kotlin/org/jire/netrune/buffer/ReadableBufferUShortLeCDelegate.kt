package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortLeCDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortLeC()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
