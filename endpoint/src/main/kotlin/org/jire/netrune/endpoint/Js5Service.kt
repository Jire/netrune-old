package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.DecoderException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Js5Service(
    private val js5Responses: Js5Responses
) : Service {

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        if (!input.isReadable(4)) return false

        val serviceVersion = input.readInt()
        logger.trace("serviceVersion: {}", serviceVersion)

        ctx.writeAndFlush(js5AcceptServiceBuf.retainedDuplicate(), ctx.voidPromise())
        ctx.read() // interested in requests
        return true
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        if (!input.isReadable(REQUEST_SIZE)) return this

        when (val opcode = input.readUnsignedByte().toInt()) {
            0, 1 -> {
                val archive = input.readUnsignedByte().toInt()
                val group = input.readUnsignedShort()

                val response = js5Responses[archive, group]
                    ?: throw DecoderException("Invalid group request ($archive:$group)")

                if (opcode == 1)
                    logger.info(
                        "Group request ({}:{}) of size {}",
                        archive,
                        group,
                        response.readableBytes()
                    )
                ctx.write(response.retainedDuplicate(), ctx.voidPromise())
            }

            2, 3, 4, 5, 6 -> input.skipBytes(3)

            else -> throw DecoderException("Unsupported JS5 opcode: $opcode")
        }

        return this
    }

    override fun readComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    private companion object {

        private const val REQUEST_SIZE = 4

        private val logger: Logger = LoggerFactory.getLogger(Js5Service::class.java)

        private val js5AcceptServiceBuf: ByteBuf =
            Unpooled.directBuffer(1, 1)
                .writeByte(0)
    }

}