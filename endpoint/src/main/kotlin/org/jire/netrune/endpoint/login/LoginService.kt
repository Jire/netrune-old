package org.jire.netrune.endpoint.login

import io.netty.channel.ChannelHandlerContext
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.DecodeMessage
import org.jire.netrune.endpoint.Rsa.rsa
import org.jire.netrune.endpoint.Session
import org.openrs2.buffer.readString
import java.util.concurrent.Executor

class LoginService(
    private val executor: Executor
) : AbstractService(32) {

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: DecodeMessage) {
        when (message) {
            is LoginConnectMessage -> {
                executor.execute {
                    // TODO: decode XTEA data to determine proof difficulty

                    val proofText = ProofOfWork.generateText()
                    session.proofText = proofText
                    val request = ProofOfWork.generateRequest(
                        session.proofDifficulty, proofText,
                        ctx.alloc()
                    )

                    ctx.writeAndFlush(request, ctx.voidPromise())

                    ctx.read() // interested in response
                }
            }

            is LoginProofOfWorkMessage -> {
                executor.execute {
                    val validated = ProofOfWork.validate(
                        session.proofDifficulty, session.proofText,
                        message.nonce
                    )
                    require(validated)

                    // TODO: decode RSA data and pass to game server
                }
            }

            else -> throw UnsupportedOperationException("Unsupported message: $message")
        }
    }

    init {
        setDecoder(16, LoginConnectMessageDecoder)
        setDecoder(18, LoginConnectMessageDecoder)

        setDecoder(19, LoginProofOfWorkMessageDecoder)
    }

    fun sendResponse(ctx: ChannelHandlerContext, responseCode: Int, sessionKey: Long) {
        val bufCapacity = 1 + 8
        val buf = ctx.alloc().buffer(bufCapacity, bufCapacity)
            .writeByte(responseCode)
            .writeLong(sessionKey)
        ctx.write(buf, ctx.voidPromise())
    }

    fun handleRsa(message: LoginConnectMessage) {
        when (message.encryptType) {
            0 -> {
                val data = message.encryptedData.rsa()

                val check = data.readUnsignedByte().toInt()
                require(check == 1)

                val xteaKeys = IntArray(4) {
                    data.readInt()
                }
                val sessionId = data.readLong()

                if (message.reconnect) {
                    for (i in 0..xteaKeys.lastIndex) {
                        val previousKey = data.readInt()
                        val key = xteaKeys[i]
                        require(previousKey == key)
                    }
                }

                val authType = data.readUnsignedByte().toInt()
                var identifier = -1
                var authCode = -1
                when (authType) {
                    0 -> identifier = data.readInt()
                    1, 3 -> {
                        authCode = data.readUnsignedMedium()
                        data.skipBytes(1)
                    }

                    2 -> data.skipBytes(4)

                    else -> throw UnsupportedOperationException("Unsupported auth type: $authType")
                }

                data.skipBytes(1)

                val password = data.readString()
            }

            else -> throw UnsupportedOperationException(
                "Unsupported encryption type: ${message.encryptType}"
            )
        }
    }

}