package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfClearItems : OsrsGameOutPacket {

    val widget: Widget

}
