package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface MoveMinimapClick : InPacket {

    val destX: Int
    val destY: Int

    val type: Int

    val width: Int
    val height: Int

    val angle: Int

    val localX: Int
    val localY: Int

}
