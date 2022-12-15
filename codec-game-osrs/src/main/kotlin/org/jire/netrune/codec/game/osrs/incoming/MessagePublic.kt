package org.jire.netrune.codec.game.osrs.incoming

interface MessagePublic : OsrsGameInPacket {

    val type: Int
    val colour: Int
    val effect: Int

    val messageLength: Int
    val message: String

}
