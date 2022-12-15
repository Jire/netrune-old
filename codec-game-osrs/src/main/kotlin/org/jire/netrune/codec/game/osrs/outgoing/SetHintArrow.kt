package org.jire.netrune.codec.game.osrs.outgoing

interface SetHintArrow : OsrsGameOutPacket {

    val type: HintArrowType

    interface HintArrowType

}
