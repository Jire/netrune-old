package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket
import org.jire.netrune.codec.osrs.game.Widget

interface ResumePauseButton : InPacket {

    val widget: Widget

    val slotId: Int

}
