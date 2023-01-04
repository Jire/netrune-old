package org.jire.netrune.gpi

interface AvatarExtension {

    val bitMask: Int

    val isUpdated: Boolean

    fun prepare()

    fun build()

}
