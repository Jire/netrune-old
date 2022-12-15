package org.jire.netrune.codec.game.osrs.v209.decode

import org.jire.netrune.codec.FixedPacketDecoder
import org.jire.netrune.codec.game.osrs.incoming.EventCameraPosition

class EventCameraPositionDecoder : FixedPacketDecoder(53, 4), EventCameraPosition {

    override val pitch by uShort
    override val angle by uShortLe

}
