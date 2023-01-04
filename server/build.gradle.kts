plugins {
    netrune

    application

    id("me.champeau.jmh") version "0.6.8"
}

dependencies {
    implementation(projects.netServerNetty4)

    val jmh = "1.36"
    implementation("org.openjdk.jmh:jmh-core:$jmh")
    /*    implementation("org.openjdk.jmh:jmh-generator-annprocess:$jmh")
        annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:$jmh")*/
    runtimeOnly(libs.slf4j.simple)
}

application {
    mainClass.set("org.jire.netrune.server.Main")
    applicationDefaultJvmArgs = listOf(
        "-XX:-OmitStackTraceInFastThrow",
        "--add-opens=java.base/java.time=ALL-UNNAMED",
        "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED",
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
        "--add-opens=java.base/java.io=ALL-UNNAMED",
        "--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED"
    )
}
