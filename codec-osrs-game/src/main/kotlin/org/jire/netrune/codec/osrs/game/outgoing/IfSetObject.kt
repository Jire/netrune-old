package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfSetObject : OutPacket {

    val widget: Widget

    val itemId: Int

    val zoom: Int

}
