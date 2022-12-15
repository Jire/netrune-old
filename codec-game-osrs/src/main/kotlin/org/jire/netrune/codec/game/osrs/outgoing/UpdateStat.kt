package org.jire.netrune.codec.game.osrs.outgoing

interface UpdateStat : OsrsGameOutPacket {

    val skillId: Int
    val currentStat: Int
    val experience: Int

}
