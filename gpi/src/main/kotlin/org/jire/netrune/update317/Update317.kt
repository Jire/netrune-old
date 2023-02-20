package org.jire.netrune.update317

import io.netty.buffer.UnpooledByteBufAllocator
import org.jire.netrune.gpikris.BitBuf
import org.jire.netrune.net.netty4.fixedDirectBuffer

object Update317 {

    const val PLAYER_CAPACITY = 2048

    val players: Array<Avatar?> = arrayOfNulls(PLAYER_CAPACITY)

    @JvmStatic
    fun main(args: Array<String>) {
        val buf = UnpooledByteBufAllocator.DEFAULT.fixedDirectBuffer(40000)
        BitBuf(buf).use { packet ->
            buf.writeByte(81)
            buf.writeZero(2) // placeholder for size

            for (player in players) {
                player ?: continue


            }

            val packetSize = buf.readableBytes() - 1 /* opcode */ - 2 /* size */
            buf.setShort(1, packetSize)
        }
    }

}