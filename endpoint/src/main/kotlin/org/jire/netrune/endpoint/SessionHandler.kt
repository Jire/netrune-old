package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.timeout.IdleStateEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.channels.ClosedChannelException

class SessionHandler(
    private val session: Session
) : SimpleChannelInboundHandler<IncomingMessage>() {

    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        logger.info("Channel active (remote={}, local={})", channel.remoteAddress(), channel.localAddress())

        ctx.read() // interested in decoding
    }

    override fun channelRead0(ctx: ChannelHandlerContext, message: IncomingMessage) {
        val session = session
        session.service.handle(session, ctx, message)
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        val session = session
        session.service.readComplete(session, ctx)
    }

    override fun channelWritabilityChanged(ctx: ChannelHandlerContext) {
        val session = session
        session.service.writabilityChanged(session, ctx)
    }

    override fun userEventTriggered(ctx: ChannelHandlerContext, evt: Any) {
        if (evt is IdleStateEvent) {
            ctx.close()
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
        private val logger: Logger = LoggerFactory.getLogger(SessionDecoder::class.java)
    }

}