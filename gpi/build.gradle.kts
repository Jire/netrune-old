import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    netrune
    id("me.champeau.jmh")
}

dependencies {
    api(projects.codec)

    api(projects.netServer)
    api(projects.netNetty4)

    api(libs.chronicle.core)

    api(projects.bufferPanama)

    runtimeOnly(libs.slf4j.simple)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xno-param-assertions"
    }
}
