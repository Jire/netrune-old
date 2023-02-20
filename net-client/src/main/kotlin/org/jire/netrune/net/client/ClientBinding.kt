package org.jire.netrune.net.client

import java.net.SocketAddress
import java.util.concurrent.Future

interface ClientBinding {

    val localAddress: SocketAddress

    val bindFuture: Future<*>

    val channelCloseFuture: Future<*>

}