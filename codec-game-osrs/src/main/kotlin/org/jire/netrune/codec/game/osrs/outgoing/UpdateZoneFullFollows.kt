package org.jire.netrune.codec.game.osrs.outgoing

interface UpdateZoneFullFollows : OsrsGameOutPacket {

    val zoneX: Int
    val zoneY: Int

}
