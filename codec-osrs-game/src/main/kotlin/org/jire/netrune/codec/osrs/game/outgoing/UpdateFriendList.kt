package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateFriendList : OutPacket {

    val friends: List<Friend>

    interface Friend {
        val displayName: String
        val previousDisplayName: String?
        val world: Int
        val refresh: Boolean
    }

}
