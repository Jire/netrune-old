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

                    handleRsa(session)
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

    private fun handleRsa(
        session: Session,
        message: LoginConnectMessage = session.loginConnectMessage
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
        message: LoginConnectMessage = session.loginConnectMessage,
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