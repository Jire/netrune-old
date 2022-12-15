package org.jire.netrune.codec.game.osrs.outgoing

interface RunClientScript : OsrsGameOutPacket {

    val scriptId: Int

    val arguments: List<Any>

}
