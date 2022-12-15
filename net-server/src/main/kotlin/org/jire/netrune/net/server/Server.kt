package org.jire.netrune.net.server

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.util.concurrent.Future

interface Server : AutoCloseable {

    fun bind(localAddress: SocketAddress): ServerBinding

    fun bind(inetPort: Int) = bind(InetSocketAddress(inetPort))

    fun bind(inetHost: String, inetPort: Int) = bind(InetSocketAddress(inetHost, inetPort))

    fun bind(inetHost: InetAddress, inetPort: Int) = bind(InetSocketAddress(inetHost, inetPort))

    fun shutdown(): List<Future<*>>

    override fun close() {
        for (future in shutdown())
            future.get()
    }

}
