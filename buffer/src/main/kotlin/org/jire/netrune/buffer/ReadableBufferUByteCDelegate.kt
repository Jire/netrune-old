package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUByteCDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.uByteC()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
