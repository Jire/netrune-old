package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface RebuildRegion : OutPacket {

    val zoneX: Int
    val zoneY: Int

}
