package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferUShortLeADelegate : ReadableBufferDelegate {

    var value = Int.MIN_VALUE

    override fun read(buffer: ReadableBuffer) {
        value = buffer.shortLeA()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
