package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface OpLocT : InPacket {

    val widget: Widget

    val id: Int

    val x: Int
    val y: Int

    val itemId: Int
    val slotId: Int

    val isCtrlMovement: Boolean

}
