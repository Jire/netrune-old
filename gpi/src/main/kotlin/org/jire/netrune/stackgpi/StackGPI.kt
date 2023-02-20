package org.jire.netrune.stackgpi

import io.netty.buffer.Unpooled
import java.lang.foreign.MemorySession
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

object StackGPI {

    const val MAX_PLAYERS = 2048
    const val PLAYER_COUNTL = 2048L

    val playerActors = arrayOfNulls<PlayerActor>(MAX_PLAYERS)

    val appearances = Array(MAX_PLAYERS) {
        NettyBuf(Unpooled.buffer(128, 512))
        //UnpooledUnsafeDirectByteBuf(UnpooledByteBufAllocator.DEFAULT, 128, 512)
    }

    fun init() {
        for (i in 0..playerActors.lastIndex) {
            val player = PlayerActor()
            playerActors[i] = player
        }
    }

    fun run() {
        val buffer = NettyBuf(Unpooled.buffer(40000, 40000))
        val maskBuffer = NettyBuf(Unpooled.directBuffer(750))

        val activityFlags = ByteArray(MAX_PLAYERS)
        val localIndexes = ShortArray(MAX_PLAYERS)
        val externalIndexes = ShortArray(MAX_PLAYERS)
        val localPlayers = arrayOfNulls<PlayerActor>(MAX_PLAYERS)
        val coordinateOffsets = ByteArray(MAX_PLAYERS)
        var externalIndexesCount = 0
        var localIndexesCount = 0

        MemorySession.openConfined().use { memorySession ->
            val packetBuffer = memorySession.allocate(40000)
            val chatBuffer = memorySession.allocate(5000)
            val k = MemorySegmentBuf(packetBuffer)

            val activityFlags = memorySession.allocate(PLAYER_COUNTL)
            val localIndexes = memorySession.allocate(PLAYER_COUNTL * 2)
            //val externalIndexes
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        init()
        while (true) {
            val elapsed = measureNanoTime {
                run()
            }
            println("Updated $MAX_PLAYERS players in ${TimeUnit.NANOSECONDS.toMillis(elapsed)}ms")
        }
    }

}