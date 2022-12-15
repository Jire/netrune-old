package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfCloseSub : OsrsGameOutPacket {

    val widget: Widget

}
