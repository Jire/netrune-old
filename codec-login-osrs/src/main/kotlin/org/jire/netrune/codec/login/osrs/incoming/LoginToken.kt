package org.jire.netrune.codec.login.osrs.incoming

interface LoginToken {

    val tokenType: Int

    val bytes: ByteArray

}
