package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface If3ButtonAction : OsrsGameInPacket {

    val slotId: Int

    val itemId: Int

    val widget: Widget

    val optionId: Int

}
