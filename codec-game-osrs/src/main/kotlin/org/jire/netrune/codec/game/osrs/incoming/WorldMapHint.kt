package org.jire.netrune.codec.game.osrs.incoming

interface WorldMapHint : OsrsGameInPacket {

    val bitPackedCoordinates: Int

}
