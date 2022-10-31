@file:Suppress("UnstableApiUsage")

rootProject.name = "build-src"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(
    "extended-version-catalog-builder",

    "project-plugin"
)
