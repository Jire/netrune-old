package org.jire.netrune.codec.game.osrs.incoming

interface OpPlayer : OsrsGameInPacket {

    val optionId: Int

    val playerIndex: Int

    val isCtrlMovement: Boolean

}
