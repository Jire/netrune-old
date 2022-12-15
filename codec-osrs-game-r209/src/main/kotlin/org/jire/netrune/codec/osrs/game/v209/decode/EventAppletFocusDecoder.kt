package org.jire.netrune.codec.osrs.game.v209.decode

import org.jire.netrune.codec.FixedPacketDecoder
import org.jire.netrune.codec.osrs.game.incoming.EventAppletFocus

class EventAppletFocusDecoder : FixedPacketDecoder(39, 1), EventAppletFocus {

    override val type by byte

}
