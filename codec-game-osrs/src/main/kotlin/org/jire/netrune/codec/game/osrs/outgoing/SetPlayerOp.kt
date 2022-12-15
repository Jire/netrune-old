package org.jire.netrune.codec.game.osrs.outgoing

interface SetPlayerOp : OsrsGameOutPacket {

    val slot: Int

    val isTop: Boolean

    val option: String

}
