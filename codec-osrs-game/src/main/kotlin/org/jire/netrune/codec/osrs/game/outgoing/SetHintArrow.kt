package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface SetHintArrow : OutPacket {

    val type: HintArrowType

    interface HintArrowType

}
