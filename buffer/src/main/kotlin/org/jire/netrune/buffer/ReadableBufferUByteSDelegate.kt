package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUByteSDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.uByteS()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
