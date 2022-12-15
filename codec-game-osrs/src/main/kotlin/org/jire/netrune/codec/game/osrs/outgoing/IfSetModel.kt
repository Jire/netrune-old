package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetModel : OsrsGameOutPacket {

    val widget: Widget

    val modelId: Int

}
