package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferByteDelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readByte().toInt()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
