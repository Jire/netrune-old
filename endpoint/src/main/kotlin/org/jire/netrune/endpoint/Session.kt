package org.jire.netrune.endpoint

import org.jire.netrune.endpoint.login.LoginConnectMessage
import org.openrs2.crypto.StreamCipher

interface Session {

    var service: Service

    var decodeCipher: StreamCipher

    var serverKey: Long

    var proofDifficulty: Int
    var proofText: String

    var loginConnectMessage: LoginConnectMessage

}