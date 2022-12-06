package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface EventMouseClick : InPacket {

    val flags: Int

    val x: Int
    val y: Int

    val isMouseButton get() = (flags and 1) == 1
    val timeSinceLastMovement get() = flags ushr 1

}
