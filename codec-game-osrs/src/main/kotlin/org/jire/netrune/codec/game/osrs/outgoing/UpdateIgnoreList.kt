package org.jire.netrune.codec.game.osrs.outgoing

interface UpdateIgnoreList : OsrsGameOutPacket {

    val ignoreList: List<IgnoredUser>

    interface IgnoredUser {
        val displayName: String
        val previousDisplayName: String?
        val refresh: Boolean
    }

}
