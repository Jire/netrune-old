package org.jire.netrune.codec.game.osrs.outgoing

interface UpdateFriendList : OsrsGameOutPacket {

    val friends: List<Friend>

    interface Friend {
        val displayName: String
        val previousDisplayName: String?
        val world: Int
        val refresh: Boolean
    }

}
