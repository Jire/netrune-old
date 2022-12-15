package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetColour : OsrsGameOutPacket {

    val widget: Widget

    val colour: Int

}
