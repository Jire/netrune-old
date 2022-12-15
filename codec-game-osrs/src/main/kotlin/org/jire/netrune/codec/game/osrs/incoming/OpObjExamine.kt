package org.jire.netrune.codec.game.osrs.incoming

interface OpObjExamine : OsrsGameInPacket {

    val itemId: Int

    val x: Int
    val y: Int

}
