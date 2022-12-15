package org.jire.netrune.server

import org.jire.netrune.net.server.netty4.Netty4Server
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Main {

    private const val PORT = 43594

    @JvmStatic
    fun main(args: Array<String>) {
        Netty4Server().use { server ->
            logger.info("Binding server to port {}...", PORT)
            val binding = server.bind(PORT)
            binding.bindFuture.get()
            logger.info("Server bound to {}", binding.localAddress)
            binding.channelCloseFuture.get()
        }
    }

    private val logger: Logger = LoggerFactory.getLogger(Main::class.java)

}
