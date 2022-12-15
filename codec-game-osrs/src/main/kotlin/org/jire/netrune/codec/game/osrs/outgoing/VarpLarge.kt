package org.jire.netrune.codec.game.osrs.outgoing

interface VarpLarge : OsrsGameOutPacket {

    val varpId: Int

    val value: Int

}
