package org.jire.netrune.codec.game.osrs.incoming

interface EventCameraPosition : OsrsGameInPacket {

    val pitch: Int
    val angle: Int

}
