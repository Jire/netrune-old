package org.jire.netrune.endpoint

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class ServerChannelInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        ch.pipeline()
            .addLast(ByteToPacketDecoder())
    }

}
