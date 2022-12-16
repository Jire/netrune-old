package org.jire.netrune.codec.login.osrs.outgoing

interface ProofOfWorkNeeded : LoginResponse {

    val a: Int
    val b: Int

    val difficulty: Int
    val nonce: String

    val c: Int

}
