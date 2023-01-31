import org.gradle.api.Project

const val NETRUNE_DEFAULT_GROUP = "org.jire.netrune"
const val NETRUNE_DEFAULT_VERSION = "0.1.0-SNAPSHOT"

internal fun Project.netrune() {
    plugins.apply("org.jetbrains.kotlin.jvm")

    group = NETRUNE_DEFAULT_GROUP
    version = NETRUNE_DEFAULT_VERSION
}
