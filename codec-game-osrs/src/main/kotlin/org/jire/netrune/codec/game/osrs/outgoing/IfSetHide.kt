package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetHide : OsrsGameOutPacket {

    val widget: Widget

    val isHidden: Boolean

}
