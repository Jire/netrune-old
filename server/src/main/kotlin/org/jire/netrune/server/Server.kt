package org.jire.netrune.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandler
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.DefaultBootstrapFactory.eventLoopGroup
import org.jire.netrune.net.DefaultBootstrapFactory.serverBootstrap

class Server(
    val parentGroup: EventLoopGroup = eventLoopGroup(1),
    val childGroup: EventLoopGroup = eventLoopGroup(),
    val childHandler: ChannelHandler = ServerChannelInitializer(),
    val bootstrap: ServerBootstrap = serverBootstrap(parentGroup, childGroup).apply {
        childHandler(childHandler)
    }
) {

    fun bind(port: Int) = bootstrap.bind(port)

}
