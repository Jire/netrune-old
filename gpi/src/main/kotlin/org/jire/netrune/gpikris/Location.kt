package org.jire.netrune.gpikris

interface Location {

    val x: Int
    val y: Int
    val z: Int

    val chunkX: Int
    val chunkY: Int

    val localChunkX: Int
    val localChunkY: Int

    val regionX: Int
    val regionY: Int

    val localX: Int
    val localY: Int

    val subChunkX: Int
    val subChunkY: Int

    val packedCoord: Int
    val packedCoordOffset: Int

    fun delta(a: Int, b: Int) = Constants.fastAbs(a - b)
    fun deltaWithinDistance(a: Int, b: Int, distance: Int) = delta(a, b) <= distance

    fun withinDistance(location: Location, distance: Int): Boolean {
/*        val regionDistance = distance ushr 6
        if (regionDistance != 0) {
            val regionCheck = regionDistance + 1
            if (!deltaWithinDistance(regionX, location.regionX, regionCheck)) return false
            if (!deltaWithinDistance(regionY, location.regionY, regionCheck)) return false
        }*/
/*        val dx = abs(x - location.x)
        if (dx > distance) return false
        val dy = abs(y - location.y)
        return dy <= distance*/

        return z == location.z
                && deltaWithinDistance(x, location.x, distance)
                && deltaWithinDistance(y, location.y, distance)
/*        return deltaWithinDistance(subChunkX, location.subChunkX, distance)
                && deltaWithinDistance(subChunkY, location.subChunkY, distance)*/

/*        val chunkDistance = distance ushr 3
        if (chunkDistance != 0) {
            val chunkCheck = chunkDistance + 1
            if (!deltaWithinDistance(chunkX, location.chunkX, chunkCheck)) return false
            if (!deltaWithinDistance(chunkY, location.chunkY, chunkCheck)) return false
        }

        return deltaWithinDistance(x, location.x, distance)
                && deltaWithinDistance(y, location.y, distance)*/
    }

}
