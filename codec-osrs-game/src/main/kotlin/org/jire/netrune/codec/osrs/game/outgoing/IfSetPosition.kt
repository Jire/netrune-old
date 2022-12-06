package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfSetPosition : OutPacket {

    val widget: Widget

    val x: Int
    val y: Int

}
