package org.jire.netrune.stresser.netty4

import io.netty.bootstrap.Bootstrap
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.client.Client
import org.jire.netrune.net.client.netty4.Netty4Client
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.stresser.Stresser
import org.jire.netrune.stresser.StresserTarget
import java.net.SocketAddress

class Netty4Stresser(
    override val target: StresserTarget,

    private val group: EventLoopGroup = DefaultEventLoopGroupFactory.eventLoopGroup(),
    private val bootstrap: Bootstrap
) : Stresser {

    private val address: SocketAddress = target.address

    override fun connect(): Client =
        Netty4Client(
            group,
            bootstrap
        ).apply {
            connect(address)
        }

    override fun close() {
        group.shutdownGracefully()
    }

}