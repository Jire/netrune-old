package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface VarpLarge : OutPacket {

    val varpId: Int

    val value: Int

}
