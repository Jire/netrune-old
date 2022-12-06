package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateStat : OutPacket {

    val skillId: Int
    val currentStat: Int
    val experience: Int

}
