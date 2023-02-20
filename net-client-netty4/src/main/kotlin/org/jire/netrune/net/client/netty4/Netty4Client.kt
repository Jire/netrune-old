package org.jire.netrune.net.client.netty4

import io.netty.bootstrap.Bootstrap
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.client.Client
import org.jire.netrune.net.client.ClientBinding
import java.net.SocketAddress

class Netty4Client(
    val group: EventLoopGroup,

    val bootstrap: Bootstrap
) : Client {

    override fun connect(remoteAddress: SocketAddress): ClientBinding {
        val bindFuture = bootstrap.connect(remoteAddress)
        val channelCloseFuture = bindFuture.channel().closeFuture()
        return Netty4ClientBinding(
            remoteAddress,
            bindFuture, channelCloseFuture
        )
    }

    override fun shutdown() = listOf(
        group.shutdownGracefully()
    )

}