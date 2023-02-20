package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

interface Js5Responses {

    fun load()

    operator fun get(bitpack: Int): ByteBuf?

    operator fun get(archive: Int, group: Int) = get(bitpack(archive, group))

    companion object {

        @JvmStatic
        fun bitpack(archive: Int, group: Int): Int {
            require(archive and 0xFF.inv() == 0) { "invalid archive $archive:$group" }
            require(group and 0xFFFF.inv() == 0) { "invalid group $archive:$group" }
            return (archive and 0xFF) or ((group and 0xFFFF) shl 8)
        }

    }

}