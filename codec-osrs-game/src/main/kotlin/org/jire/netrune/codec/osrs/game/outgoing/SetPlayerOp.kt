package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface SetPlayerOp : OutPacket {

    val slot: Int

    val isTop: Boolean

    val option: String

}
