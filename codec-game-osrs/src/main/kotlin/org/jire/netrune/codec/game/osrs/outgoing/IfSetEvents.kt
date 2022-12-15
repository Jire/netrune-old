package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetEvents : OsrsGameOutPacket {

    val widget: Widget

    val startIndex: Int
    val endIndex: Int

    val events: Int

}
