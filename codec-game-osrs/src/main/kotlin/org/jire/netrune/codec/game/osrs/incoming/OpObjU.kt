package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpObjU : OsrsGameInPacket {

    val widget: Widget

    val itemSlot: Int
    val itemId: Int
    val groundItemId: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
