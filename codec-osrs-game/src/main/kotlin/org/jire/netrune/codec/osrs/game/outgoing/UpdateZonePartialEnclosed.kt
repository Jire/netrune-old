package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateZonePartialEnclosed : OutPacket {

    val zoneX: Int
    val zoneY: Int

    val packets: List<OutPacket>

}
