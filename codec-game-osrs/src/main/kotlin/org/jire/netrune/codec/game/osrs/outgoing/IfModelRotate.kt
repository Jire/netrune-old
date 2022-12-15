package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfModelRotate : OsrsGameOutPacket {

    val widget: Widget

    val roll: Int
    val pitch: Int

}
