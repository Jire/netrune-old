package org.jire.netrune.codec.game.osrs.outgoing

interface SetPrivateChatFilter : OsrsGameOutPacket {

    val filter: Int

}
