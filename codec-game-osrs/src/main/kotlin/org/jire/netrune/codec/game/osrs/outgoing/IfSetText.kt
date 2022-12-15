package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetText : OsrsGameOutPacket {

    val widget: Widget

    val text: String

}
