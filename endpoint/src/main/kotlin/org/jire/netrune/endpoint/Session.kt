package org.jire.netrune.endpoint

import org.jire.netrune.endpoint.login.LoginConnectMessage
import org.openrs2.crypto.StreamCipher

interface Session {

    var service: Service

    var serverSeed: Long

    var decodeCipher: StreamCipher
    var encodeCipher: StreamCipher

    var proofDifficulty: Int
    var proofText: String

    var loginConnectMessage: LoginConnectMessage

}