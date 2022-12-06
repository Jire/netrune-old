package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface IgnoreListDelete : InPacket {

    val ignoreName: String

}
