package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface WindowStatus : InPacket {

    val mode: Int

    val width: Int
    val height: Int

}
