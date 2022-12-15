package org.jire.netrune.net.server

import java.net.SocketAddress
import java.util.concurrent.Future

interface ServerBinding {

    val localAddress: SocketAddress

    val bindFuture: Future<*>

    val channelCloseFuture: Future<*>

}
