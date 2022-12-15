package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpPlayerT : OsrsGameInPacket {

    val widget: Widget

    val slotId: Int
    val itemId: Int

    val playerIndex: Int

    val isCtrlMovement: Boolean

}
