package org.jire.netrune.gpi.player

import io.netty.buffer.Unpooled
import org.jire.netrune.gpi.AvatarExtensions
import org.jire.netrune.gpikris.BitBuf

class PlayerExtensions : AvatarExtensions {

    @Volatile
    override var isUpdated: Boolean = false
        private set

    val data = BitBuf(Unpooled.directBuffer(1024))

    val say = SayPlayerExtension()

    private val extensions = arrayOf(
        say,
    )

    override fun prepare() {
        for (extension in extensions) {
            if (extension.isUpdated) {
                extension.prepare()
            }
        }
    }

}
