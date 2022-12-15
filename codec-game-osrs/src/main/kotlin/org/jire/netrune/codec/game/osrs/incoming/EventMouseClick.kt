package org.jire.netrune.codec.game.osrs.incoming

interface EventMouseClick : OsrsGameInPacket {

    val flags: Int

    val x: Int
    val y: Int

    val isMouseButton get() = (flags and 1) == 1
    val timeSinceLastMovement get() = flags ushr 1

}
