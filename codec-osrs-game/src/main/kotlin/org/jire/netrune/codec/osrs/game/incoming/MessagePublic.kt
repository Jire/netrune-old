package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface MessagePublic : InPacket {

    val type: Int
    val colour: Int
    val effect: Int

    val messageLength: Int
    val message: String

}
