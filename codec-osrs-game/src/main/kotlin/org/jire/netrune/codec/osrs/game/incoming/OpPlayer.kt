package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface OpPlayer : InPacket {

    val optionId: Int

    val playerIndex: Int

    val isCtrlMovement: Boolean

}
