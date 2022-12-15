package org.jire.netrune.codec.game.osrs.outgoing

interface LogoutTransfer : OsrsGameOutPacket {

    val address: String

    val worldId: Int

    val flag: Int

}
