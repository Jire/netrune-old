package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface EventMouseMove : InPacket {

    val averageTimePerIteration: Int
    val excessTime: Int

    val movements: List<MouseMovement>

    interface MouseMovement {

        val xOffset: Int
        val yOffset: Int

        val timeSinceLastMovement: Int

    }

}
