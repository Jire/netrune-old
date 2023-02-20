package org.jire.netrune.endpoint

import com.google.common.hash.Hashing
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import org.openrs2.buffer.writeString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigInteger
import java.util.concurrent.ThreadLocalRandom

class ProofOfWorkService(
    private val connectData: ConnectData?,
    private val proofDifficulty: Int = DEFAULT_DIFFICULTY
) : Service {

    private var length = -1

    private lateinit var proofText: String

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        val timestamp = System.currentTimeMillis()
        val world = 1
        val randomData = ByteArray(495)
        ThreadLocalRandom.current().nextBytes(randomData)

        proofText = timestamp.toString(16) +
                world.toString(16) +
                BigInteger(randomData).toString(16)

        val proofTextBytes = proofText.toByteArray()
        val proofTextSize = proofTextBytes.size

        val bufSize = 1 + 2 + 1 + 1 + 1 + proofTextSize + 1
        val buf = ctx.alloc().buffer(bufSize, bufSize).apply {
            writeByte(69) // proof-of-work response code
            writeShort(4 + proofTextSize)
            writeByte(0)
            writeByte(1)
            writeByte(proofDifficulty)
            writeString(proofText)
            /*writeBytes(proofTextBytes)
            writeByte(0)*/ // terminate string
        }
        ctx.writeAndFlush(buf, ctx.voidPromise())

        logger.trace("Sent pow \"{}\"", proofText)

        ctx.read()
        return true
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        if (length == -1) {
            if (!input.isReadable(3)) return this
            val opcode = input.readUnsignedByte().toInt()
            logger.trace("pow opcode: {}", opcode)
            length = input.readUnsignedShort()
            logger.trace("pow length: $length")
        }

        if (!input.isReadable(length)) {
            logger.trace("not readable yet.")
            return this
        }

        val nonce = input.readLong()
        logger.trace("nonce: $nonce")

        val str = 1.toString(16) +
                proofDifficulty.toString(16) +
                proofText +
                nonce.toString(16)

        @Suppress("UnstableApiUsage") val hash = Hashing.sha256().hashBytes(str.toByteArray()).asBytes()
        val trailingBits = hash.countTrailingZeroBits()

        val validated = trailingBits >= proofDifficulty
        logger.trace("validated? $validated")

        return GameService(connectData)
    }

    private companion object {

        private const val DEFAULT_DIFFICULTY = 16

        private fun ByteArray.countTrailingZeroBits(): Int {
            var bits = 0
            for (b in this) {
                val n = b.countTrailingZeroBits()
                bits += n
                if (n != 8) break
            }
            return bits
        }

        private val logger: Logger = LoggerFactory.getLogger(ProofOfWorkService::class.java)

    }

}