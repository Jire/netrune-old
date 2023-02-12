import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.version

const val DEFAULT_KOTLIN_VERSION = "1.8.20-Beta"

@Suppress("UnstableApiUsage")
internal fun Settings.netrune(kotlinVersion: String = DEFAULT_KOTLIN_VERSION) {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

    dependencyResolutionManagement {
        repositories {
            mavenCentral()
            gradlePluginPortal()
            maven(url = "https://repo.runelite.net/")
            maven(url = "https://repo.openrs2.org/repository/openrs2-snapshots/")
        }
    }

    pluginManagement {
        plugins {
            kotlin("jvm") version kotlinVersion
            id("com.github.johnrengelman.shadow") version "7.1.2"
            id("me.champeau.jmh") version "0.6.8"
        }
    }

    versionCatalog {
        dependencies {
            "fastutil"("it.unimi.dsi", "8.5.11")
            "runelite-cache"("net.runelite", "1.9.8", "cache")

            group("io.netty", "netty", "4.1.87.Final") {
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

            "chronicle-core"("net.openhft", "2.24ea5")
            "chronicle-bytes"("net.openhft", "2.24ea3")

            group("org.openrs2", "openrs2", "0.1.0-SNAPSHOT") {
                "openrs2-buffer"()
                "openrs2-crypto"()
            }

            group("org.jetbrains.kotlin", "kotlin", kotlinVersion) {
                "kotlin-scripting-common"()
                "kotlin-script-runtime"()
            }

            group("org.slf4j", "slf4j", "2.0.6") {
                "slf4j-api"()
                "slf4j-simple"()
            }

            group("org.openjdk.jmh", "jmh", "1.36") {
                "jmh-core"()
            }
        }
    }
}
