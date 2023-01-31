import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.maven

const val DEFAULT_KOTLIN_VERSION = "1.8.0"

@Suppress("UnstableApiUsage")
internal fun Settings.netrune(kotlinVersion: String = DEFAULT_KOTLIN_VERSION) {
    dependencyResolutionManagement {
        repositories {
            mavenCentral()
            gradlePluginPortal()
            maven(url = "https://repo.runelite.net/")
            maven(url = "https://repo.openrs2.org/repository/openrs2-snapshots/")
        }
    }
    pluginManagement.plugins.apply {
        kotlin("jvm").version(kotlinVersion)
    }

    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
}
