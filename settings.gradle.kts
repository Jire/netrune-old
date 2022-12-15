rootProject.name = "netrune"

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-src")
}

plugins {
    id("netrune-settings")
}

versionCatalog {
    dependencies {
        "fastutil"("it.unimi.dsi", "8.5.9")
        "runelite-cache"("net.runelite", "1.9.5", "cache")

        group("io.netty", "netty", "4.1.86.Final") {
            "netty-buffer"()
            "netty-codec"()
            "netty-handler"()

            "netty-transport"()
            "netty-transport-native-epoll"()
            "netty-transport-native-kqueue"()
        }
        "netty-incubator-transport-native-iouring"(
            "io.netty.incubator",
            "0.0.16.Final",
            "netty-incubator-transport-native-io_uring"
        )

        "chronicle-core"("net.openhft", "2.23.37")
        "chronicle-bytes"("net.openhft", "2.23.33")

        group("org.openrs2", "openrs2", "0.1.0-SNAPSHOT") {
            "openrs2-buffer"()
            "openrs2-crypto"()
        }

        group("org.jetbrains.kotlin", "kotlin", "1.8.0-RC") {
            "kotlin-scripting-common"()
            "kotlin-script-runtime"()
        }

        group("org.slf4j", "slf4j", "2.0.6") {
            "slf4j-api"()
            "slf4j-simple"()
        }
    }
}

include(
    "logging",
    "common",

    "bytes",
    "codec",

    "buffer",
    "buffer-netty4",

    "net",
    "net-netty4",

    "net-server",
    "net-server-netty4",

    "codec-js5",
    "codec-js5-osrs",

    "codec-osrs-game",
    "codec-osrs-game-r209",

    "cache",
    "server",
)
