package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface ChatFilterSettings : OutPacket {

    val publicFilterSetting: Int
    val tradeFilterSetting: Int

}
