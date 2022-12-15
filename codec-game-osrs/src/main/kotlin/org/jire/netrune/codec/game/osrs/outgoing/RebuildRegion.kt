package org.jire.netrune.codec.game.osrs.outgoing

interface RebuildRegion : OsrsGameOutPacket {

    val zoneX: Int
    val zoneY: Int

}
