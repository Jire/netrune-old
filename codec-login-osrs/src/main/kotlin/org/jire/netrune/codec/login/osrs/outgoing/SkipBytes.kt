package org.jire.netrune.codec.login.osrs.outgoing

interface SkipBytes : LoginResponse {

    val bytesToSkip: Int

}
