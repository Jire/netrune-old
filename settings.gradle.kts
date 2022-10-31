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
}

include(
    "cache",
    "server",
)
