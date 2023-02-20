plugins {
    netrune
    `maven-publish`
}

dependencies {
    api(libs.openrs2.buffer)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])

        pom {
            packaging = "jar"
        }
    }
}