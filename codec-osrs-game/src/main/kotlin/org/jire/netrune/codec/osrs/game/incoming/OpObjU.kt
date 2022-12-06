package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface OpObjU : InPacket {

    val widget: Widget

    val itemSlot: Int
    val itemId: Int
    val groundItemId: Int

    val x: Int
    val y: Int

    val isCtrlMovement: Boolean

}
