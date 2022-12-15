package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpObjT : OsrsGameInPacket {

    val widget: Widget

    val slotId: Int
    val itemId: Int
    val groundItemId: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
