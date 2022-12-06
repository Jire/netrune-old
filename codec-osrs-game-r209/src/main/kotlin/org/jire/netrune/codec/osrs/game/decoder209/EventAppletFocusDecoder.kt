package org.jire.netrune.codec.osrs.game.decoder209

import org.jire.netrune.codec.FixedPacketDecoder
import org.jire.netrune.codec.osrs.game.incoming.EventAppletFocus

class EventAppletFocusDecoder : FixedPacketDecoder(39, 1), EventAppletFocus {

    override val type by byte

}
