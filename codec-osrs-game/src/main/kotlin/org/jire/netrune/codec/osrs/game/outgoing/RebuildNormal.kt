package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface RebuildNormal : OutPacket {

    val zoneX: Int
    val zoneY: Int

}
