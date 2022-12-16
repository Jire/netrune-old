package org.jire.netrune.codec.login.osrs.incoming

interface LoginRsaBlock {

    val blockType: Int

    val blockLength: Int

    val keyType: Int

    val key: LoginXteaKey

    val sessionId: Long

    val previousKey: LoginXteaKey?

    val token: LoginToken

}
