package org.jire.netrune.endpoint

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import org.jire.netrune.endpoint.js5.Js5Responses

class EndpointChannelInitializer(
    private val js5Responses: Js5Responses
) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        ch.pipeline()
            .addLast("decoder", EndpointChannelDecoder(js5Responses))
    }

}
