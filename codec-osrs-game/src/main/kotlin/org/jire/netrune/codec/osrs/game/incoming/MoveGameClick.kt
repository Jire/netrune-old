package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface MoveGameClick : InPacket {

    val destX: Int
    val destY: Int

    val type: Int

}
