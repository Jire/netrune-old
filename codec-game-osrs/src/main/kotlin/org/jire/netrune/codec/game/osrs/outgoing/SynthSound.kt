package org.jire.netrune.codec.game.osrs.outgoing

interface SynthSound : OsrsGameOutPacket {

    val soundId: Int

    val repetitions: Int
    val delay: Int

}
