import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    netrune
    id("me.champeau.jmh")
}

dependencies {
    api(projects.codec)

    api(projects.netServer)
    api(projects.netNetty4)

    implementation(libs.chronicle.core)

    implementation(projects.bufferPanama)

    runtimeOnly(libs.slf4j.simple)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xno-param-assertions"
    }
}
