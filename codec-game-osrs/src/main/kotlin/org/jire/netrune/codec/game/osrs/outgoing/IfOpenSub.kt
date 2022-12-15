package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfOpenSub : OsrsGameOutPacket {

    val interfaceId: Int

    val paneWidget: Widget

    val isWalkable: Boolean

}
