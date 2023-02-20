package org.jire.netrune.net.client

import java.net.SocketAddress
import java.util.concurrent.Future

interface Client : AutoCloseable {

    fun connect(remoteAddress: SocketAddress): ClientBinding

    fun shutdown(): List<Future<*>>

    override fun close() {
        for (future in shutdown())
            future.get()
    }

}
