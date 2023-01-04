package org.jire.netrune.gpikris

object Constants {
    const val MAX_SERVER_BUFFER_SIZE = 40_000
    const val MAX_PLAYER_INFO_SIZE = MAX_SERVER_BUFFER_SIZE - 5000

    @Suppress("NOTHING_TO_INLINE")
    inline fun fastAbs(value: Int): Int {
        val mask = value shr 31
        return (value xor mask) - mask
    }
}
