package org.jire.netrune.gpikris

interface Player : Entity {

    val flags: Flags

    val x: Int
    val y: Int
    val z: Int

    val chunkX: Int
    val chunkY: Int

    val regionX: Int
    val regionY: Int

    val packedCoord: Int
    val packedCoordOffset: Int

    val subChunkX: Int
    val subChunkY: Int

    fun withinDistance(x: Int, y: Int, z: Int, distance: Int): Boolean

}
