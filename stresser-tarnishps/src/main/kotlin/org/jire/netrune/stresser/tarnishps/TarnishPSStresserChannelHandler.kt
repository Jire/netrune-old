package org.jire.netrune.stresser.tarnishps

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.DecoderException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.math.BigInteger

internal class TarnishPSStresserChannelHandler(
    private val username: String,
    private val password: String
) : ByteToMessageDecoder() {

    private var serverSeed = INITIAL_SERVER_SEED

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush(
            handshakeBuf.retainedDuplicate(),
            ctx.voidPromise()
        )
        ctx.read() // we're interested in handshake response
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        if (serverSeed == INITIAL_SERVER_SEED) {
            ctx.read() // in case we need to retry
        }
    }

    private fun decodeHandshake(ctx: ChannelHandlerContext, buf: ByteBuf) {
        if (!buf.isReadable(8 + 1 + 8)) return

        buf.skipBytes(8)
        val handshakeResponse = buf.readUnsignedByte().toInt()
        serverSeed = buf.readLong()
        if (handshakeResponse != 0)
            throw DecoderException("Invalid handshake response for \"$username\": $handshakeResponse")
        //logger.info("[\"{}\"] handshakeResponse={}, serverSeed={}", username, handshakeResponse, serverSeed)

        encodeLogin(ctx, buf.alloc())
    }

    private fun encodeLogin(ctx: ChannelHandlerContext, alloc: ByteBufAllocator) {
        val rsa = encodeRSA(alloc)
        val rsaSize = rsa.size

        val out = ctx.alloc().buffer()
            .writeByte(NEW_CONNECTION_OPCODE)
            .writeByte(rsaSize + 1 + 40) // add 1 to account for size byte, 40 is hardcoded in client
            .writeByte(MAGIC_NUMBER)
            .writeByte(CLIENT_VERSION)
            .writeByte(CACHE_VERSION)
            .writeString(GAME_VERSION)
            .writeByte(0) // memoryVersion
        out.writeZero(4 * 9) // crcs
        out.writeByte(rsaSize)
        out.writeBytes(rsa)

        ctx.writeAndFlush(out, ctx.voidPromise())

        ctx.read() // we're interested in login response
    }

    private fun encodeRSA(alloc: ByteBufAllocator): ByteArray {
        val rsa = alloc.heapBuffer()
            .writeByte(10) // rsa
            .writeZero(4 * 5) // seed (client writes last value twice)
            .writeInt(0) // uid
            .writeString("") // UUID
            .writeString("") // macAddress
            .writeString(username) // username
            .writeString(password) // password
        val rsaArray = ByteArray(rsa.readableBytes())
        rsa.readBytes(rsaArray)
        rsa.release()
        return BigInteger(rsaArray)
            .modPow(RSA_EXPONENT, RSA_MODULUS)
            .toByteArray()
    }

    private fun decodeLogin(buf: ByteBuf) {
        val loginResponse = buf.readUnsignedByte().toInt()
        if (loginResponse != 2)
            throw DecoderException("Invalid login response for \"$username\": $loginResponse")

        val rights = buf.readUnsignedByte().toInt()
        val flagged = buf.readUnsignedByte().toInt() == 1
        logger.info("[\"{}\"] loginResponse={}, rights={}, flagged={}", username, loginResponse, rights, flagged)
    }

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        if (serverSeed == INITIAL_SERVER_SEED) {
            decodeHandshake(ctx, buf)
        } else {
            decodeLogin(buf)
        }
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        if (cause !is IOException) {
            ctx.close()
            logger.error("exceptionCaught", cause)
        }
    }

    private companion object {

        val logger: Logger = LoggerFactory.getLogger(TarnishPSStresserChannelHandler::class.java)

        const val INITIAL_SERVER_SEED = -1L

        const val LOGIN_HANDSHAKE = 14

        const val NEW_CONNECTION_OPCODE = 16

        const val MAGIC_NUMBER = 255
        const val CLIENT_VERSION = 1
        const val CACHE_VERSION = 1
        const val GAME_VERSION = "1"

        const val STRING_DELIMITER_317 = 10

        fun ByteBuf.writeString(value: String): ByteBuf = apply {
            writeBytes(value.toByteArray(Charsets.UTF_8))
            writeByte(STRING_DELIMITER_317)
        }

        val RSA_MODULUS = BigInteger(
            "q7ndelupow1ltu1gayjozuv94jcy8vg9z7pkkornnmzt7v49l2148ensr5rgxk11fmsezyhkvfdo3b5sf7rvp4xbqrtet0hgsexlgpd7nbqudf6m6w0901l03gzinj5she4dvetj9q46mpe3xexama61bmr32ko84vzpkiyydvervetra7k2pss2oq25ao6muysq15",
            Character.MAX_RADIX
        )
        val RSA_EXPONENT = BigInteger("10001", 16)

        val handshakeBuf = Unpooled.directBuffer(2, 2)
            .writeByte(LOGIN_HANDSHAKE)
            .writeByte(0) // nameHash
    }

}