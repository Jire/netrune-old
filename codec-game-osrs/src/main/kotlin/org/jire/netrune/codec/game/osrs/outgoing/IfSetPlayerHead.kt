package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetPlayerHead : OsrsGameOutPacket {

    val widget: Widget

}
