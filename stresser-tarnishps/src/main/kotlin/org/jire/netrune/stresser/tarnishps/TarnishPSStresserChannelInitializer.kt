package org.jire.netrune.stresser.tarnishps

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

internal class TarnishPSStresserChannelInitializer(
    private val username: String,
    private val password: String
) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val handler: ChannelHandler = TarnishPSStresserChannelHandler(username, password)

        ch.pipeline()
            .addLast("handler", handler)
    }

}