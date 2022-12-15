package org.jire.netrune.codec.game.osrs.incoming

interface EventKeyboard : OsrsGameInPacket {

    val numOfKeys: Int

    val keys: List<KeyboardKey>

    interface KeyboardKey {

        val msSinceLastKeypress: Int

        val keyCode: Int

    }

}
