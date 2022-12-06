package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface ResumePObjDialog : InPacket {

    val input: Int

}
