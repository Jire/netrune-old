package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface EventCameraPosition : InPacket {

    val pitch: Int
    val angle: Int

}
