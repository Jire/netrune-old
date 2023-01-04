package org.jire.netrune.gpi.jmh

import io.netty.buffer.Unpooled
import net.openhft.chronicle.core.Jvm
import org.jire.netrune.gpi.*
import org.jire.netrune.gpikris.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.Executors
import java.util.concurrent.Phaser
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(
    value = 1, warmups = 0, jvmArgsAppend = ["--add-exports=java.base/jdk.internal.ref=ALL-UNNAMED",
        "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED",
        "--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
        "--add-opens=jdk.compiler/com.sun.tools.javac=ALL-UNNAMED",
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
        "--add-opens=java.base/java.io=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED"]
)
@Measurement(time = 5, iterations = 3)
@Warmup(iterations = 1, time = 5)
open class PlayerInfoBenchmark {

    val players = Array<Player?>(2048) { index ->
        //if (index > 1024) return@Array null
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
    val exec = Executors.newWorkStealingPool()

    @Setup
    fun setup() {
        Jvm.init()
    }

    @Benchmark
    fun multiThreaded(blackhole: Blackhole) {
        phaser.bulkRegister(runnables.size)
        for (runnable in runnables) {
            exec.submit(runnable)
        }
        phaser.arriveAndAwaitAdvance()
    }

    @Benchmark
    fun singleThreaded(blackhole: Blackhole) {
        for (info in playerInfos) {
            info ?: continue
            blackhole.consume(info.encode())
        }
    }

}
