package org.jire.netrune.codec.osrs.game.v209.decode

import org.jire.netrune.codec.VarBytePacketDecoder
import org.jire.netrune.codec.osrs.game.incoming.Command

class CommandDecoder : VarBytePacketDecoder(41), Command {

    override val name by string

}
