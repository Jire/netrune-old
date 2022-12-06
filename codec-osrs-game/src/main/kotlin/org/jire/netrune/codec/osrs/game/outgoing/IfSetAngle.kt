package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfSetAngle : OutPacket {

    val widget: Widget

    val rotationX: Int
    val rotationY: Int

    val modelZoom: Int

}
