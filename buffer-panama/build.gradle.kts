import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    netrune
    application
}

dependencies {
    api(projects.buffer)
}

application {
    mainClass.set("org.jire.netrune.buffer.panama.TestKt")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.8"
}