package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface ResumePauseButton : OsrsGameInPacket {

    val widget: Widget

    val slotId: Int

}
