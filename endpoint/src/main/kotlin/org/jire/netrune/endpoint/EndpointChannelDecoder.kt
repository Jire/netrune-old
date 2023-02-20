package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.DecoderException
import org.jire.netrune.endpoint.js5.Js5Responses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.channels.ClosedChannelException

class EndpointChannelDecoder(
    private val js5Responses: Js5Responses
) : ByteToMessageDecoder() {

    private var serviceOpcode = INITIAL_SERVICE_OPCODE

    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        logger.info("Channel active (remote={}, local={})", channel.remoteAddress(), channel.localAddress())

        ctx.read() // interested in decoding
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        logger.info("Readable {} bytes", input.readableBytes())

        when (serviceOpcode) {
            INITIAL_SERVICE_OPCODE -> {
                serviceOpcode = input.readUnsignedByte().toInt()
                logger.info("serviceOpcode={}", serviceOpcode)
                decode(ctx, input, out)
            }

            JS5_SERVICE_OPCODE -> {
                if (!input.isReadable(4)) return

                val opcode = input.readUnsignedByte().toInt()
                logger.info("JS5 opcode {}", opcode)
                when (opcode) {
                    0, 1 -> {
                        val archive = input.readUnsignedByte().toInt()
                        val group = input.readUnsignedShort()

                        logger.info("Group request ({}:{})", archive, group)

                        val response = js5Responses[archive, group]
                            ?: throw DecoderException("Invalid group request ($archive:$group)")

                        ctx.writeAndFlush(response.retainedDuplicate(), ctx.voidPromise())
                    }

                    2, 3, 4, 5, 6 -> input.skipBytes(3)

                    else -> throw DecoderException("Unsupported JS5 opcode: $opcode")
                }
            }
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

        private const val INITIAL_SERVICE_OPCODE = -1
        private const val JS5_SERVICE_OPCODE = 15
    }

}
