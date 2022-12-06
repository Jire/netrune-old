package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface FriendListAdd : InPacket {

    val friendName: String

}
