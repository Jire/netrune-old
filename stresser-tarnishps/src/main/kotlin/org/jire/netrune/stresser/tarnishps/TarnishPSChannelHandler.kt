package org.jire.netrune.stresser.tarnishps

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.DecoderException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigInteger

class TarnishPSChannelHandler(
    private val username: String,
    private val password: String
) : ByteToMessageDecoder() {

    private var serverSeed = -1L

    override fun channelActive(ctx: ChannelHandlerContext) {
        val req = ctx.alloc().buffer()
            .writeByte(LOGIN_HANDSHAKE)
            .writeByte(0) // nameHash
        ctx.writeAndFlush(
            req,
            ctx.voidPromise()
        )
    }

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        if (serverSeed == -1L) {
            if (!buf.isReadable(8 + 1 + 8)) return
            buf.skipBytes(8)
            val responseCode = buf.readUnsignedByte().toInt()
            serverSeed = buf.readLong()
            logger.info("Handshake responseCode={}, serverSeed={}", responseCode, serverSeed)
            if (responseCode != 0)
                throw DecoderException("Invalid handshake response: $responseCode")

            val req = ctx.alloc().buffer()
                .writeByte(NEW_CONNECTION_OPCODE)

            val payload = ctx.alloc().buffer()
                .writeByte(MAGIC_NUMBER)
                .writeByte(CLIENT_VERSION)
                .writeByte(CACHE_VERSION)
                .writeString(GAME_VERSION)
                .writeByte(1) // memoryVersion
                .writeZero(4 * 9) // crcs

            val rsa = ctx.alloc().heapBuffer()
                .writeByte(10) // rsa
                .writeLong(0) // clientHalf
                .writeLong(0) // serverHalf
                .writeInt(0) // uid
                .writeString("") // UUID
                .writeString("") // macAddress
                .writeString(username) // username
                .writeString(password) // password
            val encryptedRsaArray = BigInteger(rsa.array())
                .modPow(RSA_EXPONENT, RSA_MODULUS)
                .toByteArray()
            rsa.release()

            payload.writeByte(encryptedRsaArray.size)
            payload.writeBytes(encryptedRsaArray)

            req.writeByte(payload.readableBytes())
            req.writeBytes(payload)
            payload.release()

            ctx.writeAndFlush(req, ctx.voidPromise())
        } else {
            val loginResponse = buf.readUnsignedByte().toInt()
            logger.info("loginResponse={}", loginResponse)
            if (loginResponse != 2)
                throw DecoderException("Invalid login response: $loginResponse")

            val rights = buf.readUnsignedByte().toInt()
            val flagged = buf.readUnsignedByte().toInt() == 1
            logger.info("rights={}, flagged={}", rights, flagged)
        }
    }

    private companion object {

        private val logger: Logger = LoggerFactory.getLogger(TarnishPSChannelHandler::class.java)

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
            String(
                byteArrayOf(
                    49,
                    48,
                    50,
                    51,
                    53,
                    51,
                    48,
                    51,
                    56,
                    57,
                    48,
                    48,
                    50,
                    53,
                    53,
                    56,
                    57,
                    49,
                    53,
                    50,
                    55,
                    54,
                    49,
                    57,
                    51,
                    54,
                    55,
                    57,
                    52,
                    49,
                    52,
                    54,
                    48,
                    54,
                    51,
                    52,
                    54,
                    51,
                    57,
                    48,
                    55,
                    56,
                    57,
                    52,
                    52,
                    50,
                    55,
                    55,
                    49,
                    52,
                    57,
                    56,
                    54,
                    57,
                    53,
                    51,
                    52,
                    55,
                    54,
                    53,
                    52,
                    52,
                    49,
                    55,
                    48,
                    49,
                    55,
                    54,
                    53,
                    48,
                    54,
                    49,
                    57,
                    49,
                    53,
                    52,
                    56,
                    48,
                    49,
                    57,
                    51,
                    57,
                    49,
                    48,
                    50,
                    57,
                    49,
                    54,
                    57,
                    53,
                    55,
                    52,
                    50,
                    55,
                    48,
                    54,
                    48,
                    52,
                    50,
                    51,
                    56,
                    54,
                    51,
                    52,
                    48,
                    54,
                    49,
                    54,
                    55,
                    51,
                    49,
                    57,
                    55,
                    51,
                    51,
                    56,
                    48,
                    48,
                    51,
                    50,
                    50,
                    56,
                    56,
                    49,
                    50,
                    55,
                    52,
                    53,
                    53,
                    52,
                    57,
                    52,
                    51,
                    53,
                    54,
                    48,
                    51,
                    49,
                    54,
                    52,
                    54,
                    50,
                    50,
                    48,
                    57,
                    56,
                    48,
                    55,
                    57,
                    53,
                    56,
                    53,
                    50,
                    54,
                    55,
                    53,
                    50,
                    51,
                    52,
                    53,
                    50,
                    53,
                    48,
                    51,
                    49,
                    54,
                    50,
                    48,
                    51,
                    48,
                    54,
                    53,
                    51,
                    57,
                    54,
                    53,
                    54,
                    57,
                    54,
                    53,
                    54,
                    56,
                    53,
                    56,
                    48,
                    50,
                    49,
                    48,
                    48,
                    51,
                    56,
                    52,
                    57,
                    48,
                    57,
                    52,
                    52,
                    56,
                    55,
                    56,
                    48,
                    55,
                    54,
                    54,
                    57,
                    54,
                    48,
                    53,
                    57,
                    55,
                    54,
                    54,
                    52,
                    49,
                    53,
                    57,
                    51,
                    50,
                    56,
                    54,
                    52,
                    56,
                    56,
                    48,
                    51,
                    55,
                    57,
                    52,
                    50,
                    56,
                    54,
                    57,
                    52,
                    55,
                    57,
                    56,
                    52,
                    49,
                    57,
                    56,
                    55,
                    53,
                    51,
                    50,
                    49,
                    54,
                    53,
                    57,
                    49,
                    52,
                    57,
                    57,
                    51,
                    55,
                    56,
                    49,
                    48,
                    57,
                    48,
                    48,
                    48,
                    57,
                    56,
                    52,
                    54,
                    51,
                    57,
                    50,
                    50,
                    57,
                    52,
                    51,
                    48,
                    54,
                    51,
                    49,
                    54,
                    56,
                    54,
                    50,
                    54,
                    55,
                    52,
                    51,
                    50,
                    54,
                    55,
                    49,
                    51,
                    55,
                    51,
                    49,
                    48,
                    54,
                    56,
                    51,
                    56,
                    55,
                    54,
                    57,
                    49,
                    51,
                    51,
                    57,
                    51,
                    57,
                    57,
                    49,
                    51
                )
            )
        )
        val RSA_EXPONENT = BigInteger(
            String(
                byteArrayOf(54, 53, 53, 51, 55)
            )
        )
    }

}