package org.jire.netrune.endpoint

import org.jire.netrune.endpoint.login.LoginConnectMessage
import org.openrs2.crypto.NopStreamCipher
import org.openrs2.crypto.StreamCipher

class DefaultSession(
    override var service: Service,

    override var decodeCipher: StreamCipher = NopStreamCipher,

    override var serverKey: Long = 0L,

    override var proofDifficulty: Int = 16
) : Session {

    override lateinit var proofText: String

    override lateinit var loginConnectMessage: LoginConnectMessage

}