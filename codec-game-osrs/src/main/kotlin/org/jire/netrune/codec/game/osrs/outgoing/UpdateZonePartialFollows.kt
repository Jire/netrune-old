package org.jire.netrune.codec.game.osrs.outgoing

interface UpdateZonePartialFollows : OsrsGameOutPacket {

    val zoneX: Int
    val zoneY: Int

}
