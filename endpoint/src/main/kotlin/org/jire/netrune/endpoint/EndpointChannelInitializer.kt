package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class EndpointChannelInitializer(
    private val js5Responses: Js5Responses
) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val service: Service = InitService(js5Responses)
        val decoder: ChannelHandler = EndpointChannelDecoder(service)
        ch.pipeline()
            .addLast("decoder", decoder)
    }

}
