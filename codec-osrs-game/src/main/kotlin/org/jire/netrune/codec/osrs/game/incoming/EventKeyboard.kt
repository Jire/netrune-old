package org.jire.netrune.codec.osrs.game.incoming

import org.jire.netrune.codec.InPacket

interface EventKeyboard : InPacket {

    val numOfKeys: Int

    val keys: List<KeyboardKey>

    interface KeyboardKey {

        val msSinceLastKeypress: Int

        val keyCode: Int

    }

}
