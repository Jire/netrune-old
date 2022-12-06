package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface OpNpc : InPacket {

    val optionId: Int

    val npcId: Int
    val isCtrlMovement: Int

}
