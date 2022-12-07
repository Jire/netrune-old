package org.jire.netrune.net.server.netty4

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class ServerChannelInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        ch.pipeline()
            .addLast(ByteToPacketDecoder())
    }

}
