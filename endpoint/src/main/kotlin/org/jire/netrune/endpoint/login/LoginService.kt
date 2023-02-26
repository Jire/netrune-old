package org.jire.netrune.endpoint.login

import io.netty.channel.ChannelHandlerContext
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.DecodeMessage
import org.jire.netrune.endpoint.Rsa.rsa
import org.jire.netrune.endpoint.Session
import org.openrs2.buffer.readString
import org.openrs2.crypto.IsaacRandom
import org.openrs2.crypto.XteaKey
import org.openrs2.crypto.xteaDecrypt
import java.util.concurrent.Executor

class LoginService(
    private val executor: Executor
) : AbstractService(32) {

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: DecodeMessage) {
        when (message) {
            is LoginConnectMessage -> {
                session.loginConnectMessage = message
                executor.execute {
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

                    decodeRsa(session)
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

    private fun decodeRsa(
        session: Session,
        message: LoginConnectMessage = session.loginConnectMessage
    ) {
        val encryptedData = message.encryptedData
        try {
            require(message.encryptType == 0)

            val data = encryptedData.rsa()
            try {
                val check = data.readUnsignedByte().toInt()
                require(check == 1)

                val clientSeed = IntArray(4) {
                    data.readInt()
                }
                val serverSeed = IntArray(clientSeed.size) {
                    clientSeed[it] + 50
                }
                session.decodeCipher = IsaacRandom(clientSeed)
                session.encodeCipher = IsaacRandom(serverSeed)

                val sessionId = data.readLong()

                if (message.reconnect) {
                    for (i in 0..clientSeed.lastIndex) {
                        val previous = data.readInt()
                        val current = clientSeed[i]
                        require(previous == current)
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

                decodeXtea(session, message, clientSeed)
            } finally {
                data.release()
            }
        } finally {
            encryptedData.release()
        }
    }

    private fun decodeXtea(
        session: Session,
        message: LoginConnectMessage = session.loginConnectMessage,
        clientSeed: IntArray
    ) {
        val xteaData = message.xteaData
        try {
            xteaData.xteaDecrypt(
                0, message.xteaLength,
                XteaKey(clientSeed[0], clientSeed[1], clientSeed[2], clientSeed[3])
            )

            val username = xteaData.readString()
        } finally {
            xteaData.release()
        }
    }

}