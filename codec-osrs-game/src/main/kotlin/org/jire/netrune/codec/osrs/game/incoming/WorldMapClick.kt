package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface WorldMapClick : InPacket {

    val x: Int
    val y: Int
    val z: Int

    val oculusCamType: Int

}
