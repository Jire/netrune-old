package org.jire.netrune.codec.osrs.game.v209.decode

import org.jire.netrune.codec.FixedPacketDecoder
import org.jire.netrune.codec.osrs.game.incoming.EventCameraPosition

class EventCameraPositionDecoder : FixedPacketDecoder(53, 4), EventCameraPosition {

    override val pitch by uShort
    override val angle by uShortLe

}
