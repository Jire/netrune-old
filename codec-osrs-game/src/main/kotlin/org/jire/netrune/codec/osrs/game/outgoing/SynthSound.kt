package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface SynthSound : OutPacket {

    val soundId: Int

    val repetitions: Int
    val delay: Int

}
