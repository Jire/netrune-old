allprojects {
    apply(plugin = "org.gradle.maven-publish")

    val javaComponent = components.findByName("java") ?: return@allprojects
    configure<PublishingExtension> {
        publications.create<MavenPublication>("maven") {
            from(javaComponent)
        }
    }
}