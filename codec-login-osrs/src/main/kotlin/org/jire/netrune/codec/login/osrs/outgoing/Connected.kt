package org.jire.netrune.codec.login.osrs.outgoing

interface Connected : LoginResponse {

    val isAuthFulfilled: Boolean
    val authUid: ByteArray

    val isPlayerMod: Boolean
    val playerIndex: Int
    val isMembers: Boolean

    val accountHash: Long
    val accountUid: Long

}
