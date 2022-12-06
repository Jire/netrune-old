package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferStringDelegate : ReadableBufferDelegate {

    lateinit var value: String

    override fun read(buffer: ReadableBuffer) {
        value = buffer.string()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
