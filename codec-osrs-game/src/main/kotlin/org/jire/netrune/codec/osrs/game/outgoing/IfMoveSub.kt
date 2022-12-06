package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfMoveSub : OutPacket {

    val fromWidget: Widget

    val toWidget: Widget

}
