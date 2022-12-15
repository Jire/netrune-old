package org.jire.netrune.codec.game.osrs.outgoing

interface CamLookAt : OsrsGameOutPacket {

    val localX: Int
    val localY: Int

    val height: Int

    val speed: Int
    val acceleration: Int

}
