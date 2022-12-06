package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface RunClientScript : OutPacket {

    val scriptId: Int

    val arguments: List<Any>

}
