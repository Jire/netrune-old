package org.jire.netrune.codec.game.osrs.incoming

interface OpLoc : OsrsGameInPacket {

    val id: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
