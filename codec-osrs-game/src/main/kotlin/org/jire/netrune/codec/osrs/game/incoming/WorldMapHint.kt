package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface WorldMapHint : InPacket {

    val bitPackedCoordinates: Int

}
