package org.jire.netrune.codec.game.osrs.incoming

interface WorldMapClick : OsrsGameInPacket {

    val x: Int
    val y: Int
    val z: Int

    val oculusCamType: Int

}
