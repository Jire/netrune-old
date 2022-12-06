package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface MidiJingle : OutPacket {

    val jingleId: Int

}
