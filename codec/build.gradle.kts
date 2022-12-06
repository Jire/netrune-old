plugins {
    netrune
}

dependencies {
    api(projects.common)

    api(libs.netty.codec)
    api(libs.openrs2.crypto)

    api(projects.buffer)
}
