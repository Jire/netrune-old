package org.jire.netrune.codec.game.osrs.outgoing

interface CamShake : OsrsGameOutPacket {

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
