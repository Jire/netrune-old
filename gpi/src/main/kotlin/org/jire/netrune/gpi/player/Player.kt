package org.jire.netrune.gpi.player

import org.jire.netrune.gpi.Avatar
import org.jire.netrune.gpi.Position

class Player(
    override val index: Int,

    override val position: Position,
    override val previousPosition: Position,

    override val teleported: Boolean,

    override val local: LocalPlayer,
    override val external: ExternalPlayer,
    override val extensions: PlayerExtensions
) : Avatar {

    override fun prepare() {
        local.prepare(this)
        extensions.prepare()
    }

    override fun build() {
        TODO("Not yet implemented")
    }

    override fun complete() {
        TODO("Not yet implemented")
    }

}
