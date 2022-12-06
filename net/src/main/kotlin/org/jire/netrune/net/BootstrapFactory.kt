package org.jire.netrune.net

import io.netty.bootstrap.Bootstrap
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.kqueue.KQueue
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueServerSocketChannel
import io.netty.channel.kqueue.KQueueSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.incubator.channel.uring.*

interface BootstrapFactory {

    fun eventLoopGroup(threads: Int = 0): EventLoopGroup =
        when {
            IOUring.isAvailable() -> IOUringEventLoopGroup(threads)
            Epoll.isAvailable() -> EpollEventLoopGroup(threads)
            KQueue.isAvailable() -> KQueueEventLoopGroup(threads)
            else -> NioEventLoopGroup(threads)
        }

    fun socketChannelClass(group: EventLoopGroup): Class<out Channel> =
        when (group) {
            is IOUringEventLoop -> IOUringSocketChannel::class.java
            is EpollEventLoopGroup -> EpollSocketChannel::class.java
            is KQueueEventLoopGroup -> KQueueSocketChannel::class.java
            else -> NioSocketChannel::class.java
        }

    fun serverSocketChannelClass(group: EventLoopGroup): Class<out ServerChannel> =
        when (group) {
            is IOUringEventLoop -> IOUringServerSocketChannel::class.java
            is EpollEventLoopGroup -> EpollServerSocketChannel::class.java
            is KQueueEventLoopGroup -> KQueueServerSocketChannel::class.java
            else -> NioServerSocketChannel::class.java
        }

    fun bootstrap(
        group: EventLoopGroup,
        channelClass: Class<out Channel> = socketChannelClass(group)
    ) = Bootstrap().apply {
        group(group)
        channel(channelClass)
    }

    fun serverBootstrap(
        parentGroup: EventLoopGroup,
        childGroup: EventLoopGroup,

        channelClass: Class<out ServerChannel> = serverSocketChannelClass(parentGroup)
    ) = ServerBootstrap().apply {
        group(parentGroup, childGroup)
        channel(channelClass)
    }

}
