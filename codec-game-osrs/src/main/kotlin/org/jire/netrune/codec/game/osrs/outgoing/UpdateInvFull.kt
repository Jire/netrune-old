package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Item
import org.jire.netrune.codec.game.osrs.Widget

interface UpdateInvFull : OsrsGameOutPacket {

    val widget: Widget

    val type: String
    val capacity: Int
    val sequence: Sequence<Item?>

}
