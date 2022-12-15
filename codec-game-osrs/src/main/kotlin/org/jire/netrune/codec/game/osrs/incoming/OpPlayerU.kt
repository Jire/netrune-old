package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpPlayerU : OsrsGameInPacket {

    val widget: Widget

    val itemSlot: Int
    val itemId: Int

    val playerIndex: Int

    val isCtrlMovement: Boolean

}
