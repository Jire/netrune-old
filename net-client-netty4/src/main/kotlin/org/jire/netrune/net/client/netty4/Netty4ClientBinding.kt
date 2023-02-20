package org.jire.netrune.net.client.netty4

import org.jire.netrune.net.client.ClientBinding
import java.net.SocketAddress
import java.util.concurrent.Future

class Netty4ClientBinding(
    override val localAddress: SocketAddress,
    override val bindFuture: Future<*>,
    override val channelCloseFuture: Future<*>
) : ClientBinding