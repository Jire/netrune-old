package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Item
import org.jire.netrune.codec.osrs.game.Widget

interface UpdateInvFull : OutPacket {

    val widget: Widget

    val type: String
    val capacity: Int
    val sequence: Sequence<Item?>

}
