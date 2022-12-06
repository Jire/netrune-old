package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface OpLoc : InPacket {

    val id: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
