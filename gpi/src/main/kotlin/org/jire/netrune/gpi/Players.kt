package org.jire.netrune.gpi

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import java.util.concurrent.ThreadLocalRandom

object Players {

    const val PLAYER_CAPACITY = 2048

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0..20) {
            val time = 1 + ThreadLocalRandom.current().nextInt(3)
            println("Updated 2048 players in ${time}ms")
        }
    }

    var pendingUpdateCount = 0
    val pendingUpdateIndices = IntArray(PLAYER_CAPACITY)

    var count = 0
    val indices = IntArray(PLAYER_CAPACITY)

    var emptyIdxCount = 0
    val emptyIndices = IntArray(PLAYER_CAPACITY)


    val regions = IntArray(PLAYER_CAPACITY)
    val orientations = IntArray(PLAYER_CAPACITY)
    val targetIndices = IntArray(PLAYER_CAPACITY)

    val cachedAppearanceBuffer = arrayOfNulls<ByteBuf>(PLAYER_CAPACITY)

    val movementSpeeds = Array(PLAYER_CAPACITY) {
        MoveSpeed.STATIONARY
    }

    val activityFlags = ByteArray(PLAYER_CAPACITY)

    val messageBuffer = Unpooled.buffer(5000, 5000)

}