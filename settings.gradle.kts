rootProject.name = "netrune"

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-src")
}

plugins {
    id("netrune-settings")
}

versionCatalog {
    "fastutil"("it.unimi.dsi", "8.5.9")
    "runelite-cache"("net.runelite", "1.8.34.1", "cache")

    group("io.netty", "netty", "4.1.84.Final") {
        "netty-buffer"()
        "netty-codec"()
        "netty-handler"()

        "netty-transport"()
        "netty-transport-native-epoll"()
        "netty-transport-native-kqueue"()
    }
    "netty-incubator-transport-native-iouring"(
        "io.netty.incubator",
        "0.0.15.Final",
        "netty-incubator-transport-native-io_uring"
    )
}

include(
    "buffer",
    "protocol",

    "cache",
    "server",
)
