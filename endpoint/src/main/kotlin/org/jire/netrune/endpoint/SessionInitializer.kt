package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit

class SessionInitializer(
    private val service: Service,

    private val timeout: Long = 30,
    private val timeoutUnit: TimeUnit = TimeUnit.SECONDS
) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val session: Session = DefaultSession(service)

        val decoder: ChannelHandler = SessionDecoder(session)
        val handler: ChannelHandler = SessionHandler(session)
        ch.pipeline()
            .addLast("idle_handler", IdleStateHandler(true, timeout, timeout, timeout, timeoutUnit))
            .addLast("decoder", decoder)
            .addLast("handler", handler)
    }

}
