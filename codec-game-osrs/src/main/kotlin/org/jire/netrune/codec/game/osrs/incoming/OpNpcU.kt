package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpNpcU : OsrsGameInPacket {

    val widget: Widget

    val npcIndex: Int

    val itemSlot: Int
    val itemId: Int

    val isCtrlMovement: Boolean

}
