package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetScrollPos : OsrsGameOutPacket {

    val widget: Widget

    val height: Int

}
