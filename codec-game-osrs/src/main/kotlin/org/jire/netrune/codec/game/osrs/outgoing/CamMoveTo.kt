package org.jire.netrune.codec.game.osrs.outgoing

interface CamMoveTo : OsrsGameOutPacket {

    val localX: Int
    val localY: Int

    val height: Int

    val speed: Int
    val acceleration: Int

}
