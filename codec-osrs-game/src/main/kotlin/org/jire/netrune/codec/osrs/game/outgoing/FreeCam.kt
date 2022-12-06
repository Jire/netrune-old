package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface FreeCam : OutPacket {

    val freeRoam: Boolean

}
