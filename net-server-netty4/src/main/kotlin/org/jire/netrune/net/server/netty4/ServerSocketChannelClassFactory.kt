package org.jire.netrune.net.server.netty4

import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueServerSocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.incubator.channel.uring.IOUringEventLoop
import io.netty.incubator.channel.uring.IOUringServerSocketChannel

interface ServerSocketChannelClassFactory {

    fun serverSocketChannelClass(group: EventLoopGroup): Class<out ServerChannel> =
        when (group) {
            is IOUringEventLoop -> IOUringServerSocketChannel::class.java
            is EpollEventLoopGroup -> EpollServerSocketChannel::class.java
            is KQueueEventLoopGroup -> KQueueServerSocketChannel::class.java
            else -> NioServerSocketChannel::class.java
        }

}
