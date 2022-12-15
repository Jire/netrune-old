package org.jire.netrune.codec.game.osrs.incoming

interface MoveMinimapClick : OsrsGameInPacket {

    val destX: Int
    val destY: Int

    val type: Int

    val width: Int
    val height: Int

    val angle: Int

    val localX: Int
    val localY: Int

}
