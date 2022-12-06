package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface OpPlayerU : InPacket {

    val widget: Widget

    val itemSlot: Int
    val itemId: Int

    val playerIndex: Int

    val isCtrlMovement: Boolean

}
