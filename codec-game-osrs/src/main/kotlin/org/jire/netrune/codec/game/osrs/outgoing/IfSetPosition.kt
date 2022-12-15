package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetPosition : OsrsGameOutPacket {

    val widget: Widget

    val x: Int
    val y: Int

}
