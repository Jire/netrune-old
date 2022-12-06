package org.jire.netrune.codec.osrs.game.outgoing

import org.jire.netrune.codec.OutPacket

interface MinimapToggle : OutPacket {

    val state: MinimapState

    @Suppress("unused")
    enum class MinimapState(val state: Int) {
        Enabled(0),
        MapUnclickable(1),
        HideMap(2),
        HideCompass(3),
        HideCompassMapUnclickable(4),
        Disabled(5)
    }

}
