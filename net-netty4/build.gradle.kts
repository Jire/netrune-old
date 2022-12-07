plugins {
    netrune
}

dependencies {
    api(projects.net)

    api(projects.bufferNetty4)

    api(libs.netty.transport)

    api(libs.netty.transport.native.epoll)
    api(libs.netty.transport.native.kqueue)
    api(libs.netty.incubator.transport.native.iouring)
}
