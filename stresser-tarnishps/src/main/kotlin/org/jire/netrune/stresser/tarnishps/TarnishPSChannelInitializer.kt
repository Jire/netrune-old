package org.jire.netrune.stresser.tarnishps

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class TarnishPSChannelInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val handler: ChannelHandler = TarnishPSChannelHandler("jire10", "hello")

        ch.pipeline()
            .addLast("handler", handler)
    }

}