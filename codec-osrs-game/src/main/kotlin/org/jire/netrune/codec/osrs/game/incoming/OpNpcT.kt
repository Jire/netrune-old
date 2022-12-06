package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface OpNpcT : InPacket {

    val widget: Widget

    val npcIndex: Int

    val slotId: Int
    val itemId: Int

    val isCtrlMovement: Boolean

}
