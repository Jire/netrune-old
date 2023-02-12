package org.jire.netrune.buffer.panama

import java.lang.foreign.MemorySession

fun main() {
    MemorySession.openConfined().use { memorySession ->
        memorySession.buffer(100).run {
            short.a(1337)
            println(short[0])
            println(short.a())
        }
    }
}