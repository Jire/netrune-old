package org.jire.netrune.gpi.player

import io.netty.buffer.Unpooled
import org.jire.netrune.gpi.Avatar
import org.jire.netrune.gpi.LocalAvatar
import org.jire.netrune.gpi.Movement.dualTileMovementDirection
import org.jire.netrune.gpi.Movement.singleTileMovementDirection
import org.jire.netrune.gpikris.BitBuf
import org.jire.netrune.gpikris.Constants.fastAbs

class LocalPlayer : LocalAvatar {

    @Volatile
    override var isUpdated: Boolean = false
        private set

    val data = BitBuf(Unpooled.directBuffer(1024))

    override fun prepare(avatar: Avatar) {
        data.writeBoolean(isUpdated)
        data.writeBoolean(avatar.extensions.isUpdated)

        val (y, x, z) = avatar.position
        val (py, px, pz) = avatar.previousPosition

        val dy = y - py
        val dx = x - px
        val dz = z - pz

        val move = dy != 0 || dx != 0 || dz != 0

        if (move) prepareMove(avatar.teleported, dy, dx, dz)
    }

    private fun prepareMove(teleported: Boolean, dy: Int, dx: Int, dz: Int) {
        val ay = fastAbs(dy)
        val ax = fastAbs(dx)
        val az = fastAbs(dz)

        val teleport = teleported || ay != 0 || ax > 2 || az > 2
        data.writeBoolean(teleport)
        if (teleport) prepareTeleport(ax > 15 || az > 15, dy, dx, dz)
        else prepareWalk(ax > 1 || az > 1, dx, dz)
    }

    private fun prepareWalk(run: Boolean, dx: Int, dz: Int) {
        data.writeBoolean(run)
        if (run) data.writeBits(4, dualTileMovementDirection(dx, dz))
        else data.writeBits(3, singleTileMovementDirection(dx, dz))
    }

    private fun prepareTeleport(far: Boolean, dy: Int, dx: Int, dz: Int) {
        data.writeBoolean(far)
        data.writeBits(2, dy and 0b111)
        val dBits = if (far) 14 else 5
        val dMask = if (far) 0x3FFF else 0x1F
        data.writeBits(dBits, dx and dMask)
        data.writeBits(dBits, dz and dMask)
    }

}
