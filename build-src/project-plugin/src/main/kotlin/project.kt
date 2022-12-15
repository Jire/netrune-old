import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

const val NETRUNE_DEFAULT_GROUP = "org.jire.netrune"
const val NETRUNE_DEFAULT_VERSION = "0.1.0-SNAPSHOT"

internal fun Project.netrune() {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = NETRUNE_DEFAULT_GROUP
    version = NETRUNE_DEFAULT_VERSION

    configure<JavaPluginExtension> {
        plugins.withType<JavaPlugin>().configureEach {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}
