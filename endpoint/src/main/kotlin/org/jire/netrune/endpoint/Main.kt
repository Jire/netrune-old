package org.jire.netrune.endpoint

import io.netty.channel.EventLoopGroup
import org.jire.netrune.endpoint.game.DefaultXteaRepository
import org.jire.netrune.endpoint.init.InitService
import org.jire.netrune.endpoint.js5.Js5GroupRepository
import org.jire.netrune.endpoint.js5.Openrs2Js5GroupRepository
import org.jire.netrune.net.netty4.DefaultEventLoopGroupFactory
import org.jire.netrune.net.netty4.EventLoopGroupFactory
import org.openrs2.cache.DiskStore
import org.openrs2.cache.Js5MasterIndex
import org.openrs2.cache.Store
import java.nio.file.Path
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        DefaultXteaRepository.load()

        val worldID = 1

        val loginExecutor: Executor = Executors.newSingleThreadExecutor()

        val storePath = Path.of("data", "cache")
        val store: Store = DiskStore.open(storePath)
        val masterIndex: Js5MasterIndex = Js5MasterIndex.create(store)

        val js5GroupRepository: Js5GroupRepository = Openrs2Js5GroupRepository(store, masterIndex)
        js5GroupRepository.load()

        val service: Service = InitService(js5GroupRepository, loginExecutor)

        val eventLoopGroupFactory: EventLoopGroupFactory = DefaultEventLoopGroupFactory
        val parentGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup(1)
        val childGroup: EventLoopGroup = eventLoopGroupFactory.eventLoopGroup()

        Endpoint(
            service,

            parentGroup, childGroup,

            intArrayOf(Endpoint.DEV_JS5_PORT, Endpoint.JS5_PORT_BASE + worldID),
            intArrayOf(Endpoint.DEV_WORLD_PORT, Endpoint.WORLD_PORT_BASE + worldID)
        ).use { endpoint ->
            endpoint.run()
        }
    }

}
