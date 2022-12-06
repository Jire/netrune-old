package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferVarIntDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.varInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
