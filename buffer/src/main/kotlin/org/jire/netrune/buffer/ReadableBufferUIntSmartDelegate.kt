package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUIntSmartDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUIntSmart()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
