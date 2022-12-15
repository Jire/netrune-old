package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetNpcHead : OsrsGameOutPacket {

    val widget: Widget

    val npcId: Int

}
