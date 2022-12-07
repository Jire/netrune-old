package org.jire.netrune.net.netty4

import io.netty.channel.EventLoopGroup
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.kqueue.KQueue
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.incubator.channel.uring.IOUring
import io.netty.incubator.channel.uring.IOUringEventLoopGroup

interface EventLoopGroupFactory {

    fun eventLoopGroup(threads: Int = 0): EventLoopGroup =
        when {
            IOUring.isAvailable() -> IOUringEventLoopGroup(threads)
            Epoll.isAvailable() -> EpollEventLoopGroup(threads)
            KQueue.isAvailable() -> KQueueEventLoopGroup(threads)
            else -> NioEventLoopGroup(threads)
        }

}
