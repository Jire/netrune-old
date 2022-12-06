package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface SetMapFlag : OutPacket {

    val x: Int
    val y: Int

}
