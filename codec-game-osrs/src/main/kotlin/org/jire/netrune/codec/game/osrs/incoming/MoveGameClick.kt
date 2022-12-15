package org.jire.netrune.codec.game.osrs.incoming

interface MoveGameClick : OsrsGameInPacket {

    val destX: Int
    val destY: Int

    val type: Int

}
