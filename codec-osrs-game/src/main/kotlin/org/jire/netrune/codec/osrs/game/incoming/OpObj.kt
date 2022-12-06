package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface OpObj : InPacket {

    val optionId: Int

    val itemId: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
