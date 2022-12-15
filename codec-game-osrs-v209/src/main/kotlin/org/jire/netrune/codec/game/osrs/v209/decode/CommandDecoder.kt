package org.jire.netrune.codec.game.osrs.v209.decode

import org.jire.netrune.codec.VarBytePacketDecoder
import org.jire.netrune.codec.game.osrs.incoming.Command

class CommandDecoder : VarBytePacketDecoder(41), Command {

    override val name by string

}
