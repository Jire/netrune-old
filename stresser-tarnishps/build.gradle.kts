plugins {
    netrune

    application
}

dependencies {
    implementation(projects.stresserNetty4)

    runtimeOnly(libs.slf4j.simple)
}

application {
    mainClass.set("org.jire.netrune.stresser.tarnishps.TarnishPSStresser")
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
