package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface CamLookAt : OutPacket {

    val localX: Int
    val localY: Int

    val height: Int

    val speed: Int
    val acceleration: Int

}
