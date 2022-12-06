package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface IfButtonT : InPacket {

    val fromWidget: Widget
    val fromSlotId: Int
    val fromItemId: Int

    val toWidget: Widget
    val toSlotId: Int
    val toItemId: Int

}
