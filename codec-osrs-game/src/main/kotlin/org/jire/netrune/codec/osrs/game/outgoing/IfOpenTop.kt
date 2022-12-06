package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface IfOpenTop : OutPacket {

    val paneId: Int

}
