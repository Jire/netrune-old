package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetObject : OsrsGameOutPacket {

    val widget: Widget

    val itemId: Int

    val zoom: Int

}
