package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferShortLeCDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readShortLeC().toInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
