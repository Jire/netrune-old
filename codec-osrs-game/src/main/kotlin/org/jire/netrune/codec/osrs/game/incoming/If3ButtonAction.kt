package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface If3ButtonAction : InPacket {

    val slotId: Int

    val itemId: Int

    val widget: Widget

    val optionId: Int

}
