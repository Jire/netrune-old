package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface LogoutTransfer : OutPacket {

    val address: String

    val worldId: Int

    val flag: Int

}
