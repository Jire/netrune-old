package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.net.netty4.EventLoopGroupFactory
import org.jire.netrune.net.server.ServerBinding
import org.jire.netrune.net.server.netty4.DefaultServerBootstrapFactory
import org.jire.netrune.net.server.netty4.Netty4Server
import org.openrs2.cache.DiskStore
import org.openrs2.cache.Js5MasterIndex
import org.openrs2.cache.Store
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path

class Endpoint(
    private val js5Responses: Js5Responses,

    private val worldId: Int,
    private val worldPort: Int = WORLD_PORT_BASE + worldId,
    private val js5Port: Int = JS5_PORT_BASE + worldId
) : Runnable, AutoCloseable {

    private val childHandler: ChannelHandler = EndpointChannelInitializer(js5Responses)
    private val eventLoopGroupFactory: EventLoopGroupFactory = DefaultEventLoopGroupFactory
    private val parentGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup(1)
    private val childGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup()
    private val bootstrap = DefaultServerBootstrapFactory.serverBootstrap(
        parentGroup, childGroup,
        childHandler = childHandler
    )
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childOption(ChannelOption.AUTO_READ, false)
        .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30_000)
    private val server = Netty4Server(
        parentGroup, childGroup,
        bootstrap
    )

    override fun run() {
        js5Responses.load()

        val bindings = bindServer()
        bindings.forEach {
            it.channelCloseFuture.get() // don't exit thread until channels are closed
            logger.warn("Channel on address \"{}\" was closed", it.localAddress)
        }
    }

    private fun bindServer(): List<ServerBinding> {
        logger.info("Binding addresses...")

        val bindings = listOf(
            server.bind(DEFAULT_WORLD_PORT),
            server.bind(DEFAULT_JS5_PORT),
            server.bind(worldPort),
            server.bind(js5Port)
        )

        bindings.forEach {
            it.bindFuture.get() // wait for bind to complete
            logger.info("Bound to address \"{}\"", it.localAddress)
        }

        return bindings
    }

    override fun close() {
        server.close()
    }

    companion object {

        const val DEFAULT_WORLD_PORT = 43594
        const val DEFAULT_JS5_PORT = 443

        const val WORLD_PORT_BASE = 40000
        const val JS5_PORT_BASE = 50000

        private val logger: Logger = LoggerFactory.getLogger(Endpoint::class.java)

        private const val DEFAULT_WORLD_ID = 1

        private val DEFAULT_STORE_PATH: Path = Path.of("data", "cache")

        @JvmStatic
        @JvmOverloads
        fun createEndpoint(
            worldID: Int = DEFAULT_WORLD_ID,
            js5Responses: Js5Responses
        ): Endpoint = Endpoint(js5Responses, worldID)

        @JvmStatic
        @JvmOverloads
        fun createEndpoint(
            worldID: Int = DEFAULT_WORLD_ID,
            store: Store, masterIndex: Js5MasterIndex
        ): Endpoint = createEndpoint(worldID, Openrs2Js5Responses(store, masterIndex))

        @JvmStatic
        @JvmOverloads
        fun createEndpoint(
            worldID: Int = DEFAULT_WORLD_ID,
            storePath: Path = DEFAULT_STORE_PATH
        ): Endpoint {
            val store: Store = DiskStore.open(storePath)
            val masterIndex = Js5MasterIndex.create(store)
            return createEndpoint(worldID, store, masterIndex)
        }

    }

}