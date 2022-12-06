package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface UpdateIgnoreList : OutPacket {

    val ignoreList: List<IgnoredUser>

    interface IgnoredUser {
        val displayName: String
        val previousDisplayName: String?
        val refresh: Boolean
    }

}
