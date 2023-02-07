package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortLeADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readUShortLeA()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
