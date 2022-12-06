package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface SetPrivateChatFilter : OutPacket {

    val filter: Int

}
