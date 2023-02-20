package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandler
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.net.netty4.EventLoopGroupFactory
import org.jire.netrune.net.server.netty4.DefaultServerBootstrapFactory
import org.jire.netrune.net.server.netty4.Netty4Server
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Main {

    private const val PORT = 43594

    @JvmStatic
    fun main(args: Array<String>) {
        val childHandler: ChannelHandler = ServerChannelInitializer()
        val eventLoopGroupFactory: EventLoopGroupFactory = DefaultEventLoopGroupFactory
        val parentGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup(1)
        val childGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup()
        val bootstrap = DefaultServerBootstrapFactory.serverBootstrap(
            parentGroup, childGroup,
            childHandler = childHandler
        )
        Netty4Server(
            parentGroup, childGroup,
            bootstrap
        ).use { server ->
            logger.info("Binding endpoint to port {}...", PORT)
            val binding = server.bind(PORT)
            binding.bindFuture.get()
            logger.info("Endpoint bound to {}", binding.localAddress)
            binding.channelCloseFuture.get()
        }
    }

    private val logger: Logger = LoggerFactory.getLogger(Main::class.java)

}