package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface OpObjExamine : InPacket {

    val itemId: Int

    val x: Int
    val y: Int

}
