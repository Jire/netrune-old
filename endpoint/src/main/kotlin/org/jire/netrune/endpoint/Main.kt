package org.jire.netrune.endpoint

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        Xteas.load()

        Endpoint.createEndpoint().use { endpoint ->
            endpoint.run()
        }
    }

}
