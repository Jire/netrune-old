package org.jire.netrune.endpoint.login

import io.netty.channel.ChannelHandlerContext
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.IncomingMessage
import org.jire.netrune.endpoint.Rsa.rsa
import org.jire.netrune.endpoint.Session
import org.jire.netrune.endpoint.login.incoming.LoginConnect
import org.jire.netrune.endpoint.login.incoming.LoginConnectDecoder
import org.jire.netrune.endpoint.login.incoming.LoginProofOfWork
import org.jire.netrune.endpoint.login.incoming.LoginProofOfWorkDecoder
import org.jire.netrune.endpoint.login.outgoing.LoginResponse
import org.jire.netrune.endpoint.login.outgoing.SolveProofOfWorkEncoder
import org.openrs2.buffer.readString
import org.openrs2.crypto.IsaacRandom
import org.openrs2.crypto.XteaKey
import org.openrs2.crypto.xteaDecrypt
import java.util.concurrent.Executor

class LoginService(
    private val executor: Executor
) : AbstractService(32) {

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: IncomingMessage) {
        when (message) {
            is LoginConnect -> {
                session.loginConnect = message
                executor.execute {
                    val proofText = ProofOfWork.generateText()
                    session.proofText = proofText

                    ctx.writeAndFlush(LoginResponse.SolveProofOfWork(session.proofDifficulty, session.proofText))
                        .addListener { future ->
                            if (future.isSuccess) {
                                ctx.read()
                            }
                        }
                }
            }

            is LoginProofOfWork -> {
                executor.execute {
                    val validated = ProofOfWork.validate(
                        session.proofDifficulty, session.proofText,
                        message.nonce
                    )
                    require(validated)

                    handleRsa(session)
                }
            }

            else -> throw UnsupportedOperationException("Unsupported message: $message")
        }
    }

    init {
        setDecoder(16, LoginConnectDecoder)
        setDecoder(18, LoginConnectDecoder)

        setDecoder(19, LoginProofOfWorkDecoder)
        setEncoder(LoginResponse.SolveProofOfWork::class, SolveProofOfWorkEncoder)
    }

    private fun handleRsa(
        session: Session,
        message: LoginConnect = session.loginConnect
    ) {
        val secureBlockData = message.secureBlockData
        try {
            require(message.secureBlockType == 0)

            val blockData = secureBlockData.rsa()
            try {
                val check = blockData.readUnsignedByte().toInt()
                require(check == 1)

                val clientXteaKey = XteaKey(
                    blockData.readInt(),
                    blockData.readInt(),
                    blockData.readInt(),
                    blockData.readInt()
                )

                val serverSeed = blockData.readLong()

                if (message.reconnect) {
                    val previousXteaKey = XteaKey(
                        blockData.readInt(),
                        blockData.readInt(),
                        blockData.readInt(),
                        blockData.readInt()
                    )
                    require(clientXteaKey == previousXteaKey)
                }

                session.decodeCipher = IsaacRandom(clientXteaKey.toIntArray())
                session.encodeCipher = IsaacRandom(clientXteaKey.toIntArray().map { it + 50 }.toIntArray())

                val authType = blockData.readUnsignedByte().toInt()
                var identifier = -1
                var authCode = -1
                when (authType) {
                    0 -> identifier = blockData.readInt()
                    1, 3 -> {
                        authCode = blockData.readUnsignedMedium()
                        blockData.skipBytes(1)
                    }

                    2 -> blockData.skipBytes(4)

                    else -> throw UnsupportedOperationException("Unsupported auth type: $authType")
                }

                blockData.skipBytes(1)

                val password = blockData.readString()

                handleXtea(session, message, clientXteaKey)
            } finally {
                blockData.release()
            }
        } finally {
            secureBlockData.release()
        }
    }

    private fun handleXtea(
        session: Session,
        message: LoginConnect = session.loginConnect,
        clientXteaKey: XteaKey
    ) {
        val xteaData = message.xteaBlockData
        try {
            xteaData.xteaDecrypt(0, message.xteaBlockLength, clientXteaKey)

            val username = xteaData.readString()
        } finally {
            xteaData.release()
        }
    }

}