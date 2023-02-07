package org.jire.netrune.buffer

import kotlin.reflect.KProperty

class ReadableBufferVersionedStringDelegate : ReadableBufferDelegate {

    lateinit var value: String

    override fun read(buffer: ReadableBuffer) {
        value = buffer.readVersionedString()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

}
