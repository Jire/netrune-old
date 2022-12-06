package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface CamShake : OutPacket {

    val type: CamShakeType

    val shakeIntensity: Int
    val movementIntensity: Int
    val speed: Int

    enum class CamShakeType(val type: Int) {
        LEFT_AND_RIGHT(0),
        UP_AND_DOWN(1),
        FRONT_AND_BACK(2),
        STRONG_LEFT_AND_RIGHT(3),
        STRONG_UP_AND_DOWN(4)
    }

}
