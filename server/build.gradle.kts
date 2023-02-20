plugins {
    netrune
    application
}

dependencies {
    implementation(projects.netServerNetty4)
    runtimeOnly(libs.slf4j.simple)
    implementation("com.displee:rs-cache-library:6.8.1")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")


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
        "--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED",
        "--enable-native-access=ALL-UNNAMED"
    )
}
