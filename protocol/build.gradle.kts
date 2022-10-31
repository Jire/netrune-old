plugins {
    netrune
}

dependencies {
    api(projects.buffer)

    api(libs.netty.codec)

    implementation(libs.netty.transport.native.epoll)
    implementation(libs.netty.transport.native.kqueue)

    implementation(libs.netty.incubator.transport.native.iouring)
}
