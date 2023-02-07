package org.jire.netrune.buffer

interface Buffer : AutoCloseable {

    fun has(bytes: Int): Boolean

}
