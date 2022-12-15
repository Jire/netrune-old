package org.jire.netrune.net.server.netty4

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.net.netty4.EventLoopGroupFactory
import org.jire.netrune.net.server.Server
import java.net.SocketAddress

class Netty4Server(
    val eventLoopGroupFactory: EventLoopGroupFactory = DefaultEventLoopGroupFactory,
    val parentGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup(1),
    val childGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup(),

    val bootstrapFactory: ServerBootstrapFactory = DefaultServerBootstrapFactory,
    val bootstrap: ServerBootstrap = bootstrapFactory.serverBootstrap(parentGroup, childGroup)
) : Server {

    override fun bind(localAddress: SocketAddress): Netty4ServerBinding {
        val bindFuture = bootstrap.bind(localAddress)
        val channelCloseFuture = bindFuture.channel().closeFuture()
        return Netty4ServerBinding(
            localAddress,
            bindFuture, channelCloseFuture
        )
    }

    override fun shutdown() = listOf(
        parentGroup.shutdownGracefully(),
        childGroup.shutdownGracefully()
    )

}
