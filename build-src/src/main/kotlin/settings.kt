import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.maven

const val DEFAULT_KOTLIN_VERSION = "1.7.20"

@Suppress("UnstableApiUsage")
internal fun Settings.netrune(kotlinVersion: String = DEFAULT_KOTLIN_VERSION) {
    dependencyResolutionManagement {
        repositories {
            mavenCentral()
            gradlePluginPortal()
            maven(url = "https://repo.runelite.net/")
        }
    }
    pluginManagement.plugins.apply {
        kotlin("jvm").version(kotlinVersion)
    }

    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
}
