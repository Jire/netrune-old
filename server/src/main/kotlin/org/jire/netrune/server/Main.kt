package org.jire.netrune.server

import org.jire.netrune.net.server.netty4.Netty4Server

object Main {

    private const val PORT = 43594

    @JvmStatic
    fun main(args: Array<String>) {
        Netty4Server()
            .bind(PORT)
    }

}
