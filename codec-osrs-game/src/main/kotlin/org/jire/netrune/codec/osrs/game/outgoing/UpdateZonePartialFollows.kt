package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateZonePartialFollows : OutPacket {

    val zoneX: Int
    val zoneY: Int

}
