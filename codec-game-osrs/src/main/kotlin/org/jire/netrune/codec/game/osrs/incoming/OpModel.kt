package org.jire.netrune.codec.game.osrs.incoming

import org.jire.netrune.codec.game.osrs.Widget

interface OpModel : OsrsGameInPacket {

    val widget: Widget

}
