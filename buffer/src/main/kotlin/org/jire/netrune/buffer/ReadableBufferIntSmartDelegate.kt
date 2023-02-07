package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferIntSmartDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readIntSmart()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
