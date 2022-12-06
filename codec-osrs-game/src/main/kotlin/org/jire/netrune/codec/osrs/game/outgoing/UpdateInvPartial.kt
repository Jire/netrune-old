package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket
import org.jire.netrune.codec.osrs.game.Item
import org.jire.netrune.codec.osrs.game.Widget

interface UpdateInvPartial : OutPacket {

    val widget: Widget

    val type: String

    val sequence: Sequence<IndexedValue<Item?>>

}
