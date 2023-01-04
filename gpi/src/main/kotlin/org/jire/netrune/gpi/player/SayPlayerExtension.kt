package org.jire.netrune.gpi.player

import io.netty.buffer.Unpooled
import org.jire.netrune.gpi.SayAvatarExtension
import org.openrs2.buffer.writeString

class SayPlayerExtension : SayAvatarExtension {

    @Volatile
    override var isUpdated = false
        private set

    @Volatile
    private lateinit var message: String

    override val bitMask = 2

    val data = Unpooled.buffer()

    override fun update(message: String) {
        this.message = message
        isUpdated = true
    }

    override fun prepare() {
        data.clear()
        data.writeString(message)
    }

    override fun build() {
    }

}
