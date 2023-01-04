package org.jire.netrune.net.server.netty4

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.jire.netrune.buffer.netty4.Netty4ReadableBuffer
import org.jire.netrune.codec.SteppedBufferDecoder
import org.jire.netrune.codec.game.osrs.v209.decode.CommandDecoder
import org.jire.netrune.net.netty4.fixedBuffer
import java.nio.channels.ClosedChannelException

class ByteToPacketDecoder : ByteToMessageDecoder() {

    private lateinit var decoder: Netty4BufferDecoder

    class MutableNetty4ReadableBuffer : Netty4ReadableBuffer {
        override lateinit var byteBuf: ByteBuf
    }

    class Netty4BufferDecoder : SteppedBufferDecoder() {
        override var input = MutableNetty4ReadableBuffer()

        lateinit var out: MutableList<Any>
        lateinit var ctx: ChannelHandlerContext

        override fun output(message: Any) {
            out.add(message)
        }

        init {
            step {
                val opcode = input.uByte()
                println("opcode: $opcode")
            }
            steps.completable {
                if (input.has(4)) {
                    val build = input.int()
                    println("build is: $build")

                    ctx.writeAndFlush(ctx.alloc().fixedBuffer(1).writeByte(0), ctx.voidPromise())
                    done()
                }
            }
        }

    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        decoder = Netty4BufferDecoder()
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        CommandDecoder().decode()
        decoder.input.byteBuf = input
        decoder.out = out
        decoder.ctx = ctx
        decoder.decode()
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()

        if (cause !is ClosedChannelException) {
            ctx.close()
        }
    }

}
