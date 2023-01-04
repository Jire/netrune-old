package org.jire.netrune.gpikris

interface Entity {

    val index: Int
    val prevIndex: Int get() = index

    val location: Location
    val previousLocation: Location get() = location
    val teleported: Boolean get() = false

    val exists: Boolean get() = true

    fun isVisibleInViewport(other: Player, distance: Int): Boolean =
        isVisibleInViewport(other.location, distance)

    fun isVisibleInViewport(location: Location, distance: Int): Boolean =
        this.location.withinDistance(location, distance)

}
