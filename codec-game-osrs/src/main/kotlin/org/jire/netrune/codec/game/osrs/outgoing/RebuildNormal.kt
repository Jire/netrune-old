package org.jire.netrune.codec.game.osrs.outgoing

interface RebuildNormal : OsrsGameOutPacket {

    val zoneX: Int
    val zoneY: Int

}
