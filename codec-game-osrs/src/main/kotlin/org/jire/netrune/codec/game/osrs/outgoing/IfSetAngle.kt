package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetAngle : OsrsGameOutPacket {

    val widget: Widget

    val rotationX: Int
    val rotationY: Int

    val modelZoom: Int

}
