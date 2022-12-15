package org.jire.netrune.codec.game.osrs.outgoing

interface ChatFilterSettings : OsrsGameOutPacket {

    val publicFilterSetting: Int
    val tradeFilterSetting: Int

}
