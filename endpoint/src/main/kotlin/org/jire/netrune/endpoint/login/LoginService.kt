package org.jire.netrune.endpoint.login

import io.netty.channel.ChannelHandlerContext
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.IncomingMessage
import org.jire.netrune.endpoint.Session
import org.jire.netrune.endpoint.login.Rsa.rsa
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
import java.math.BigInteger
import java.util.concurrent.Executor

class LoginService(
    private val executor: Executor,

    private val rsaExponent: BigInteger = BigInteger(
        "8r2hl97wkw1tqrvianejtja0vwb8v2cwlu4f3nuhacc4auh4sln4m5y4wcu0t41267wopyvua3wozis2vlpxoka060lsv9q9uf3geecofzi5nvzcduvwhrrc9eqsq5oiy3m9ttgf0mmc1z31q0xd4vhthzchaqi1copcekdyujbjnprya6neuvkhvm5rf1s299xbgin3gwiu9l4fuvzoz821tm6j6ac7rym2eisns8vcoycld0a65tpp4fvmemgte0h6gvkkmd882lawa061bgmqf7yr27ijszzhlkowcetkdbwhg8c4rbqhupxbvvdvk83302w0ijbxcukrglzu9jk3nf6aizuzk5fvi8ww7a245h5mg4lwtlx75ulzek0sh2lrab2seit5",
        36
    ),
    private val rsaModulus: BigInteger = BigInteger(
        "zu24jygdg2scwtf37mo2d82fhn0jpflhml6y5sfvldtokjkbinuvtdk4qoyfenjye2a4drui3o65o0g5f831h69j2yu3ilidrwe3zdcxvpdyng4aylmyom44bi2eppab7awucm3imtl810ta6r8swfjn38gzfo6ma8w2jx73eott735va4rpvvplmzovx0svcvn92he1mrdg5ovkmqyzi61ylw96ztuf2vfkvspi4t6253jsoh0pckwvy1bncd1nknhn7rg53aurs7m754mnyz23qepv43l7qe2jizy1u75buku3iovw9vt3nzwujbmov5p8u7vbezzryo1v181ca90yo88saxcolurhu2txdhw7aq7iwka93rzazmc4agsljdazehidn8j5",
        36
    )
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

            val blockData = secureBlockData.rsa(rsaExponent, rsaModulus)
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