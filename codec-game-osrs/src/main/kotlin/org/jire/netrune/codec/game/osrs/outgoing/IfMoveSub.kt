package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfMoveSub : OsrsGameOutPacket {

    val fromWidget: Widget

    val toWidget: Widget

}
