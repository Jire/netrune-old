package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateUid192 : OutPacket {

    val uid: ByteArray

}
