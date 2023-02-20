package org.jire.netrune.gpi

interface Avatar {

    val index: Int

    val position: Position
    val previousPosition: Position

    val teleported: Boolean

    val local: LocalAvatar
    val external: ExternalAvatar

    val extensions: AvatarExtensions

    var hasMovementPending: Boolean

    var plane: Int

    var x: Int
    var y: Int

    var tileX: Int
    var tileY: Int

    val pathX: IntArray
    val pathY: IntArray

    var orientation: Int
    var movingOrientation: Int

    var targetIndex: Int

}
