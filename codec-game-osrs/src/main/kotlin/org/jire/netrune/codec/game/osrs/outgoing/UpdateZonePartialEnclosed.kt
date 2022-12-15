package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateZonePartialEnclosed : OsrsGameOutPacket {

    val zoneX: Int
    val zoneY: Int

    val packets: List<OutPacket>

}
