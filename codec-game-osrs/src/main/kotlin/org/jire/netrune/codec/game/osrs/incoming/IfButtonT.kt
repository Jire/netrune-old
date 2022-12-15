package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface IfButtonT : OsrsGameInPacket {

    val fromWidget: Widget
    val fromSlotId: Int
    val fromItemId: Int

    val toWidget: Widget
    val toSlotId: Int
    val toItemId: Int

}
