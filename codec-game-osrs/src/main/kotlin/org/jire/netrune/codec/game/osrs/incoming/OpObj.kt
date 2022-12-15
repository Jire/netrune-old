package org.jire.netrune.codec.game.osrs.incoming

interface OpObj : OsrsGameInPacket {

    val optionId: Int

    val itemId: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
