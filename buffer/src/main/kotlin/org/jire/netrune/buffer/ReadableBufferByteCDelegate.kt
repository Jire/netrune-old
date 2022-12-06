package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferByteCDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.byteC()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
