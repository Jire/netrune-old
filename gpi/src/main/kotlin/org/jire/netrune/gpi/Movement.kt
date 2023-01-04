package org.jire.netrune.gpi

object Movement {

    fun singleTileMovementDirection(deltaX: Int, deltaY: Int): Int = when {
        deltaX == -1 && deltaY == -1 -> 0
        deltaX == 0 && deltaY == -1 -> 1
        deltaX == 1 && deltaY == -1 -> 2
        deltaX == -1 && deltaY == 0 -> 3
        deltaX == 1 && deltaY == 0 -> 4
        deltaX == -1 && deltaY == 1 -> 5
        deltaX == 0 && deltaY == 1 -> 6
        deltaX == 1 && deltaY == 1 -> 7
        else -> error("Both deltas must be in range of -1..1, and cannot both be 0 - $deltaX, $deltaY")
    }

    fun dualTileMovementDirection(deltaX: Int, deltaY: Int): Int = when {
        deltaX == -2 && deltaY == -2 -> 0
        deltaX == -1 && deltaY == -2 -> 1
        deltaX == 0 && deltaY == -2 -> 2
        deltaX == 1 && deltaY == -2 -> 3
        deltaX == 2 && deltaY == -2 -> 4
        deltaX == -2 && deltaY == -1 -> 5
        deltaX == 2 && deltaY == -1 -> 6
        deltaX == -2 && deltaY == 0 -> 7
        deltaX == 2 && deltaY == 0 -> 8
        deltaX == -2 && deltaY == 1 -> 9
        deltaX == 2 && deltaY == 1 -> 10
        deltaX == -2 && deltaY == 2 -> 11
        deltaX == -1 && deltaY == 2 -> 12
        deltaX == 0 && deltaY == 2 -> 13
        deltaX == 1 && deltaY == 2 -> 14
        deltaX == 2 && deltaY == 2 -> 15
        else -> error("Both deltas must be in range of -2..2, and cannot both be 0 - $deltaX, $deltaY")
    }

}
