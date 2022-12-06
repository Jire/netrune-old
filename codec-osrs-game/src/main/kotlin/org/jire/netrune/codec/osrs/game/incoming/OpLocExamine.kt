package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface OpLocExamine : InPacket {

    val objId: Int

}
