package org.jire.netrune.codec.game.osrs.incoming

interface WindowStatus : OsrsGameInPacket {

    val mode: Int

    val width: Int
    val height: Int

}
