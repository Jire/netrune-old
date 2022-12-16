package org.jire.netrune.codec.login.osrs.outgoing

interface JustLeftWorld : LoginResponse {

    val secondsToWait: Int

}
