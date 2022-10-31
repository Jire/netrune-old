plugins {
    `kotlin-dsl`
}

allprojects {
    group = "org.jire.netrune"
    version = "0.1.0-SNAPSHOT"
}

dependencies {
    implementation(project(":extended-version-catalog-builder"))
    implementation(project(":project-plugin"))
}

gradlePlugin.plugins.register("netrune-settings") {
    id = name
    implementationClass = "NetruneSettingsPlugin"
}
