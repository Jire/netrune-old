package org.jire.netrune.codec.login.osrs.outgoing

interface CustomMessage : LoginResponse {

    val line1: String
    val line2: String
    val line3: String

}
