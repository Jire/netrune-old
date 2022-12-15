package org.jire.netrune.codec.game.osrs.outgoing

interface UpdateRebootTimer : OsrsGameOutPacket {

    val timer: Int

}
