package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfSetEvents : OutPacket {

    val widget: Widget

    val startIndex: Int
    val endIndex: Int

    val events: Int

}
