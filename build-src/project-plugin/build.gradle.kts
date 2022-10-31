plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("gradle-plugin"))
}

gradlePlugin.plugins.register("netrune") {
    id = name
    implementationClass = "NetruneProjectPlugin"
}
