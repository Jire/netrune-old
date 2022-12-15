package org.jire.netrune.codec.game.osrs.outgoing

interface SetMapFlag : OsrsGameOutPacket {

    val x: Int
    val y: Int

}
