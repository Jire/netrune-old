rootProject.name = "netrune"

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-src")
}

plugins {
    id("netrune-settings")
}

include(
    "logging",
    "common",
    "threads",

    "rsa",

    "bytes",
    "codec",

    "buffer",
    "buffer-netty4",
    "buffer-panama",

    "net",
    "net-netty4",

    "net-server",
    "net-server-netty4",

    "net-client",
    "net-client-netty4",

    "codec-js5",
    "codec-js5-osrs",

    "codec-game",
    "codec-game-osrs",
    "codec-game-osrs-v209",

    "codec-login",
    "codec-login-osrs",
    "codec-login-osrs-v209",

    "gpi",

    "cache",
    "server",

    "stresser",
    "stresser-netty4",

    "stresser-tarnishps",
)
