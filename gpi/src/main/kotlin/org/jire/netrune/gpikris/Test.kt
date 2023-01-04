package org.jire.netrune.gpikris

import io.netty.buffer.Unpooled
import net.openhft.chronicle.core.Jvm
import java.util.concurrent.Executors
import java.util.concurrent.Phaser
import kotlin.system.measureTimeMillis

object Test {

    val players = Array<Player?>(2048) { index ->
        val location = LocationImpl(
            //ThreadLocalRandom.current().nextInt(8000), ThreadLocalRandom.current().nextInt(8000)
            3200 + (index % 32), 3200 + (index % 32), 0
        )
        PlayerImpl(index, location, FlagsImpl())
    }
    val playerInfos = Array(players.size) { index ->
        val player = players[index] ?: return@Array null
        PlayerInfo(players, player)
    }.apply {
        for (info in this) {
            info?.initialize(Unpooled.buffer())
        }
    }
    val phaser = Phaser(1)
    val runnables = playerInfos.filterNotNull().map {
        Runnable {
            it.encode()
            phaser.arriveAndDeregister()
        }
    }.toTypedArray()

    init {
        Jvm.init()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val exec = Executors.newWorkStealingPool()
        while (true) {
            val elapsed = measureTimeMillis {
/*                phaser.bulkRegister(runnables.size)
                for (runnable in runnables) {
                    exec.submit(runnable)
                }
                phaser.arriveAndAwaitAdvance()*/
                for (info in playerInfos) {
                    info?.encode()
                }
            }
            println(elapsed)
            Thread.sleep(600)
        }
    }

}
