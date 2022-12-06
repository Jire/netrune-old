package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface OpPlayerT : InPacket {

    val widget: Widget

    val slotId: Int
    val itemId: Int

    val playerIndex: Int

    val isCtrlMovement: Boolean

}
