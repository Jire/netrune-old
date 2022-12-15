package org.jire.netrune.codec.game.osrs.outgoing

interface VarpSmall : OsrsGameOutPacket {

    val varpId: Int

    val value: Int

}
