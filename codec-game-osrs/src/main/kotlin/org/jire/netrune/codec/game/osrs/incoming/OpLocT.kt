package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpLocT : OsrsGameInPacket {

    val widget: Widget

    val id: Int

    val x: Int
    val y: Int

    val itemId: Int
    val slotId: Int

    val isCtrlMovement: Boolean

}
