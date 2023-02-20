package org.jire.netrune.net.client

import java.net.InetSocketAddress
import java.util.concurrent.Future

interface Client : AutoCloseable {

    fun connect(remoteAddress: InetSocketAddress): ClientBinding

    fun shutdown(): List<Future<*>>

    override fun close() {
        for (future in shutdown())
            future.get()
    }

}
