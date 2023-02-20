package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.channels.ClosedChannelException

class EndpointChannelDecoder(
    private var service: Service
) : ByteToMessageDecoder() {

    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        logger.info("Channel active (remote={}, local={})", channel.remoteAddress(), channel.localAddress())

        ctx.read() // interested in decoding
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        val newService = service.decode(ctx, input, out)
        logger.info("New service: {}", newService::class.simpleName)
        if (service != newService && newService.init(ctx, input, out)) {
            service = newService
            logger.info("Switched to new service: {}", newService::class.simpleName)
        }
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        logger.error("", cause)

        if (cause !is ClosedChannelException) {
            ctx.close()
        }
    }

    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(EndpointChannelDecoder::class.java)
    }

}
