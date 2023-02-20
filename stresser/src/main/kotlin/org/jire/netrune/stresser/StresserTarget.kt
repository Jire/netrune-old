package org.jire.netrune.stresser

import java.net.InetSocketAddress
import java.net.SocketAddress

interface StresserTarget {

    val host: String
    val port: Int

    val address: SocketAddress
        get() = InetSocketAddress(host, port)

    companion object {

        const val DEFAULT_PORT = 43594

    }

}