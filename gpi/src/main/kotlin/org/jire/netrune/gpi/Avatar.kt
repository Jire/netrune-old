package org.jire.netrune.gpi

interface Avatar {

    val index: Int

    val position: Position
    val previousPosition: Position

    val teleported: Boolean

    val local: LocalAvatar
    val external: ExternalAvatar

    val extensions: AvatarExtensions

    fun prepare()

    fun build()

    fun complete()

}
