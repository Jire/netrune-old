package org.jire.netrune.gpikris

class LocationImpl(
    override val x: Int,
    override val y: Int,
    override val z: Int
) : Location {

    override val chunkX = x ushr 3
    override val chunkY = y ushr 3

    override val regionX = chunkX ushr 3
    override val regionY = chunkY ushr 3

    override val localChunkX = x - 64 * (regionX - 3)
    override val localChunkY = y - 64 * (regionY - 3)

    override val subChunkX = x - 64 * (regionX - 1)
    override val subChunkY = y - 64 * (regionY - 1)

    override val localX = x - 8 * (chunkX - 6)
    override val localY = y - 8 * (chunkY - 6)

    override val packedCoord = y or (x shl 14) or (z shl 28)
    override val packedCoordOffset = (y shr 13) or ((x shr 13) shl 8) or (z shr 16)

    override fun equals(other: Any?) =
        other is Location
                && other.packedCoord == packedCoord

}
