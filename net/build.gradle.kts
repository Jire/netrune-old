plugins {
    netrune
}

dependencies {
    api(libs.netty.transport)

    implementation(libs.netty.transport.native.epoll)
    implementation(libs.netty.transport.native.kqueue)
    implementation(libs.netty.incubator.transport.native.iouring)
}
