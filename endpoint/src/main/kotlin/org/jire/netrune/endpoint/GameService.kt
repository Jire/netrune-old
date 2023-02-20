package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.channel.ChannelHandlerContext
import org.openrs2.buffer.BitBuf
import org.openrs2.buffer.writeShortLEA

class GameService(
    private val connectData: ConnectData?,
) : Service {

    override fun init(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Boolean {
        val buf = ctx.alloc().buffer()
            .writeByte(2) // login response code, 2 = success

        val lengthWriterIndex = buf.writerIndex()
        buf.writeZero(1) // placeholder for length

        val payloadWriterIndex = buf.writerIndex()

        val authenticated = false
        buf.writeBoolean(authenticated)
        if (authenticated) {
            // TODO: send proper key and progress ISAAC cipher
            buf.writeZero(4)
        } else {
            buf.writeZero(4)
        }

        buf.writeByte(0) // crown
        buf.writeBoolean(false) // is moderator
        val playerIndex = 1
        buf.writeShort(playerIndex) // player index
        buf.writeBoolean(true) // has membership
        buf.writeLong(0) // account hash, used for new character picker in OSRS

        // "actual world server" encoding starts here...

        //buf.writeLong(0) // account registration ID, incrementing. First acc = 0, etc.

        val rebuildNormal = rebuildNormal(ctx.alloc(), playerIndex)
        val rebuildNormalSize = rebuildNormal.readableBytes()
        buf.writeByte(0) // TODO ISAAC
        buf.writeShort(rebuildNormalSize)

        val written = buf.writerIndex() - payloadWriterIndex
        buf.setByte(lengthWriterIndex, written)

        buf.writeBytes(rebuildNormal, 0, rebuildNormalSize)

        ctx.writeAndFlush(buf, ctx.voidPromise())

        ctx.read()

        return true
    }

    private fun rebuildNormal(alloc: ByteBufAllocator, playerIndex: Int): ByteBuf {
        val bytes = alloc.buffer()

        val bits = BitBuf(bytes)
        val x = 3200
        val y = 3200
        val z = 0
        bits.writeBits(30, positionBitpack(x, y, z))
        for (i in 1..2047) {
            if (i == playerIndex) continue // skip our player index
            val multiplier = 0 // position18Bitpack(x, y, z)
            bits.writeBits(18, multiplier)
        }

        val chunkX = x ushr 3
        val chunkY = y ushr 3
        bytes.writeShortLE(chunkY)
        bytes.writeShortLEA(chunkX)

        val xteasBuf = alloc.buffer()
        try {
            var regionCount = 0
            for (xCalc in (chunkX - 6) / 8..(chunkX + 6) / 8) {
                for (yCalc in (chunkY - 6) / 8..(chunkY + 6) / 8) {
                    val regionId = yCalc + (xCalc shl 8)
                    val xtea = Xteas[regionId]!!
                    for (key in xtea.key) {
                        xteasBuf.writeInt(key)
                    }
                    regionCount++
                }
            }
            bytes.writeShort(regionCount)
            bytes.writeBytes(xteasBuf)
        } finally {
            xteasBuf.release()
        }

        return bytes
    }

    private fun positionBitpack(x: Int, y: Int, z: Int) = y or (x shl 14) or (z shl 28)

    private fun position18Bitpack(x: Int, y: Int, z: Int) =
        y shr 13 or (x shr 13 shl 8) or (z shl 16)

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        // TODO: dynamic game packet support
        return this
    }

}