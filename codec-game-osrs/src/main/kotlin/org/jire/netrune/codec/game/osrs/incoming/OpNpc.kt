package org.jire.netrune.codec.game.osrs.incoming

interface OpNpc : OsrsGameInPacket {

    val optionId: Int

    val npcId: Int
    val isCtrlMovement: Int

}
