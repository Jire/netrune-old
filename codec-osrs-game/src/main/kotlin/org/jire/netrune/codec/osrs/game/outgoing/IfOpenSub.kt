package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfOpenSub : OutPacket {

    val interfaceId: Int

    val paneWidget: Widget

    val isWalkable: Boolean

}
