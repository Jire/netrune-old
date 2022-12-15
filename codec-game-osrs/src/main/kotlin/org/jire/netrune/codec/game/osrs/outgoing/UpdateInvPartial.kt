package org.jire.netrune.codec.game.osrs.outgoing

import org.jire.netrune.codec.game.osrs.Item
import org.jire.netrune.codec.game.osrs.Widget

interface UpdateInvPartial : OsrsGameOutPacket {

    val widget: Widget

    val type: String

    val sequence: Sequence<IndexedValue<Item?>>

}
