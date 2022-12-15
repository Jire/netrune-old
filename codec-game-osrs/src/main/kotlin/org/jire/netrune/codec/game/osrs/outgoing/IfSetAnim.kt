package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Widget

interface IfSetAnim : OsrsGameOutPacket {

    val widget: Widget

    val animationId: Int

}
