package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface MidiSong : OutPacket {

    val songId: Int

}
