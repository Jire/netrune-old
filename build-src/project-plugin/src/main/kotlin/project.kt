import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

const val NETRUNE_DEFAULT_GROUP = "org.jire.netrune"
const val NETRUNE_DEFAULT_VERSION = "0.1.0-SNAPSHOT"

internal fun Project.netrune() {
    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("org.gradle.maven-publish")

    group = NETRUNE_DEFAULT_GROUP
    version = NETRUNE_DEFAULT_VERSION

    configure<KotlinJvmProjectExtension> {
        jvmToolchain(19)
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.compilerArgs.add("--enable-preview")
    }

    val javaComponent = components.findByName("java") ?: return
    configure<PublishingExtension> {
        publications.create<MavenPublication>("maven") {
            from(javaComponent)
        }
    }
}
