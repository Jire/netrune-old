import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    netrune
    id("me.champeau.jmh") version "0.6.8"
}

dependencies {
    api(projects.codec)

    api(projects.netServer)
    api(projects.netNetty4)

    implementation("net.openhft:affinity:3.23.2")
    //implementation(libs.chronicle.core)
    implementation("net.openhft:chronicle-core:2.24ea3")
    val jmh = "1.36"
    implementation("org.openjdk.jmh:jmh-core:$jmh")

    runtimeOnly(libs.slf4j.simple)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xno-param-assertions"
    }
}
