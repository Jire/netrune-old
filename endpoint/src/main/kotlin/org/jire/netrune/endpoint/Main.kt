package org.jire.netrune.endpoint

import org.openrs2.cache.DiskStore
import org.openrs2.cache.Js5MasterIndex
import org.openrs2.cache.Store
import java.nio.file.Path

object Main {

    private const val WORLD_ID = 1

    private val storePath: Path = Path.of("data", "cache")

    @JvmStatic
    fun main(args: Array<String>) {
        Xteas.load()

        val store: Store = DiskStore.open(storePath)
        val masterIndex = Js5MasterIndex.create(store)
        val js5Responses: Js5Responses = Openrs2Js5Responses(store, masterIndex)
        Endpoint(js5Responses, WORLD_ID).use { endpoint ->
            endpoint.run()
        }
    }

}
