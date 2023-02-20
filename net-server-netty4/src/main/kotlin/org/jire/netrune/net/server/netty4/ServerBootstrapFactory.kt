package org.jire.netrune.net.server.netty4

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandler
import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel

interface ServerBootstrapFactory {

    fun serverBootstrap(
        parentGroup: EventLoopGroup,
        childGroup: EventLoopGroup,

        channelClassFactory: ServerSocketChannelClassFactory = DefaultServerSocketChannelClassFactory,
        channelClass: Class<out ServerChannel> = channelClassFactory.serverSocketChannelClass(parentGroup),

        childHandler: ChannelHandler
    ) = ServerBootstrap().apply {
        group(parentGroup, childGroup)

        channel(channelClass)

        childHandler(childHandler)
    }

}
