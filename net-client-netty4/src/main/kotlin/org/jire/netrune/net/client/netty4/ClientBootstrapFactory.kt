package org.jire.netrune.net.client.netty4

import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelHandler
import io.netty.channel.EventLoopGroup

interface ClientBootstrapFactory {

    fun clientBootstrap(
        group: EventLoopGroup,
        channelClass: Class<out Channel>,
        handler: ChannelHandler
    ) = Bootstrap().apply {
        group(group)
        channel(channelClass)
        handler(handler)
    }

}