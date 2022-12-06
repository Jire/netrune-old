package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface ResumePNameDialog : InPacket {

    val input: String

}
