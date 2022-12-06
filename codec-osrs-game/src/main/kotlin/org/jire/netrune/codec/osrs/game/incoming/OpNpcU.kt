package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface OpNpcU : InPacket {

    val widget: Widget

    val npcIndex: Int

    val itemSlot: Int
    val itemId: Int

    val isCtrlMovement: Boolean

}
