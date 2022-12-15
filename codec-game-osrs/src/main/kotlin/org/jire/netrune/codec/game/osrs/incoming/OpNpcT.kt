package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpNpcT : OsrsGameInPacket {

    val widget: Widget

    val npcIndex: Int

    val slotId: Int
    val itemId: Int

    val isCtrlMovement: Boolean

}
