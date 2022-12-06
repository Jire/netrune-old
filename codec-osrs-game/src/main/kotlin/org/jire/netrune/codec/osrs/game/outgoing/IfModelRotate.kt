package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfModelRotate : OutPacket {

    val widget: Widget

    val roll: Int
    val pitch: Int

}
