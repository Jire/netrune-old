package org.jire.netrune.codec.game.osrs.incoming

interface EventMouseMove : OsrsGameInPacket {

    val averageTimePerIteration: Int
    val excessTime: Int

    val movements: List<MouseMovement>

    interface MouseMovement {

        val xOffset: Int
        val yOffset: Int

        val timeSinceLastMovement: Int

    }

}
