package org.jire.netrune.gpikris

class PlayerImpl(
    override val index: Int,
    override val location: Location,
    override val flags: Flags
) : Player {
    override val prevIndex = index
    override val previousLocation = location
    override val teleported = false
    override val exists = true

    override val x = location.x
    override val y = location.y
    override val z = location.z

    override val chunkX = x ushr 3
    override val chunkY = y ushr 3

    override val regionX = chunkX ushr 3
    override val regionY = chunkY ushr 3

    override val subChunkX = x - 64 * (regionX - 1)
    override val subChunkY = y - 64 * (regionY - 1)

    override val packedCoord = location.packedCoord
    override val packedCoordOffset = location.packedCoordOffset

    fun delta(a: Int, b: Int) = Constants.fastAbs(a - b)
    fun deltaWithinDistance(a: Int, b: Int, distance: Int) = delta(a, b) <= distance

    override fun withinDistance(x: Int, y: Int, z: Int, distance: Int): Boolean {
        return z == this.z
                && deltaWithinDistance(x, this.x, distance)
                && deltaWithinDistance(y, this.y, distance)
    }

    override fun equals(other: Any?) =
        other is Player
                && other.index == index

}
