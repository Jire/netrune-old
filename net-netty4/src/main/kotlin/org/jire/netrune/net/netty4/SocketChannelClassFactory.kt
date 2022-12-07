package org.jire.netrune.net.netty4

import io.netty.channel.Channel
import io.netty.channel.EventLoopGroup
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.incubator.channel.uring.IOUringEventLoop
import io.netty.incubator.channel.uring.IOUringSocketChannel

interface SocketChannelClassFactory {

    fun socketChannelClass(group: EventLoopGroup): Class<out Channel> =
        when (group) {
            is IOUringEventLoop -> IOUringSocketChannel::class.java
            is EpollEventLoopGroup -> EpollSocketChannel::class.java
            is KQueueEventLoopGroup -> KQueueSocketChannel::class.java
            else -> NioSocketChannel::class.java
        }

}
