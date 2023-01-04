package org.jire.netrune.gpikris

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import org.jire.netrune.gpikris.Constants.fastAbs

/**
 * @author Kris | 11/04/2021
 */
class PlayerInfo(
    private val players: Array<Player?>,

    private val localPlayer: Player
) {

    private val activityFlags = ByteArray(MAX_PLAYERS)
    private val localIndexes = ShortArray(MAX_PLAYERS)
    private val externalIndexes = ShortArray(MAX_PLAYERS)
    private val localPlayers = arrayOfNulls<Player>(MAX_PLAYERS)
    private val coordinateOffsets = ByteArray(MAX_PLAYERS)
    private var externalIndexesCount = 0
    private var localIndexesCount = 0
    private val buffer = Unpooled.directBuffer(8192)
    private val bitBuffer = BitBuf(buffer)
    private val maskBuffer = Unpooled.directBuffer(8192)
    private val bitMaskBuffer = BitBuf(maskBuffer)
    private var resizeTickCount = RESIZE_CHECK_INTERVAL

    private val preBuffer = Unpooled.directBuffer(8192)
    private val preBitBuffer = BitBuf(preBuffer)

    var forceViewDistance = false
    var viewDistance = PREFERRED_VIEW_DISTANCE

    fun encode(): ByteBuf {
        resize()
        buffer.clear()
        bitBuffer.clear()
        maskBuffer.clear()
        bitMaskBuffer.clear()
        processLocalPlayers(true)
        processLocalPlayers(false)
        processExternalPlayers(true)
        processExternalPlayers(false)
        buffer.writeBytes(maskBuffer)
        localIndexesCount = 0
        externalIndexesCount = 0
        for (i in 1..MAX_PLAYERS - 1) {
            activityFlags[i] = (activityFlags[i].toInt() shr 1).toByte()
            if (localPlayers[i] == null) externalIndexes[externalIndexesCount++] = i.toShort()
            else localIndexes[localIndexesCount++] = i.toShort()
        }
        return buffer
    }

    fun initialize(buffer: ByteBuf) {
        bitBuffer.writeBits(30, localPlayer.packedCoord)
        localPlayers[localPlayer.index] = localPlayer
        localIndexes[localIndexesCount++] = localPlayer.index.toShort()
        for (i in 1..MAX_PLAYERS - 1) {
            if (i == localPlayer.index) continue
            val otherPlayer = players[i]
            val coordinateOffset = coordinateOffset(otherPlayer)
            coordinateOffsets[i] = coordinateOffset.toByte()
            bitBuffer.writeBits(18, coordinateOffset)
            externalIndexes[externalIndexesCount++] = i.toShort()
        }
    }

    private fun resize() {
        if (forceViewDistance) return
        if (localIndexesCount >= PREFERRED_PLAYER_COUNT) {
            if (viewDistance > 0) viewDistance--
            resizeTickCount = 0
            return
        }
        if (++resizeTickCount >= RESIZE_CHECK_INTERVAL) {
            if (viewDistance < PREFERRED_VIEW_DISTANCE) {
                viewDistance++
            } else {
                resizeTickCount = 0
            }
        }
    }

    private fun needAdd(otherPlayer: Player?): Boolean =
        otherPlayer != null
                && otherPlayer.index != localPlayer.index
                && otherPlayer.exists
                && localPlayer.withinDistance(otherPlayer.x, otherPlayer.y, otherPlayer.z, viewDistance)
                && buffer.readableBytes() + maskBuffer.readableBytes() < Constants.MAX_PLAYER_INFO_SIZE

    private fun addPlayer(otherPlayer: Player) {
        val index = otherPlayer.index
        // Notify client that there is an update coming for this player.
        bitBuffer.writeBits(1, 1)
        // Notify client not to skip anyone now.
        bitBuffer.writeBits(2, 0)
        updateCoordOffset(otherPlayer)
        bitBuffer.writeBits(13, otherPlayer.x)
        bitBuffer.writeBits(13, otherPlayer.y)
        // Notify the client that there will be a mask update for this player(appearance at the very least, if nothing else).
        bitBuffer.writeBits(1, 1)
        writeMaskBlock(otherPlayer, true)
        localPlayers[index] = otherPlayer
        activityFlags[index] = (activityFlags[index].toInt() or 2).toByte()
    }

    private fun needRemove(otherPlayer: Player): Boolean =
        otherPlayer.index != localPlayer.index
                && (!otherPlayer.exists
                || !localPlayer.withinDistance(otherPlayer.x, otherPlayer.y, otherPlayer.z, viewDistance))

    private fun removePlayer(otherPlayer: Player) {
        val index = otherPlayer.prevIndex
        // Notify the client that this player is to be removed.
        bitBuffer.writeBits(1, 1)
        // Notify the client that this player's masks do not need updating.
        bitBuffer.writeBits(1, 0)
        // Notify the client that this player's position does not need updating.
        bitBuffer.writeBits(2, 0)
        updateCoordOffset(otherPlayer)
        localPlayers[index] = null
    }

    private fun updateCoordOffset(otherPlayer: Player) {
        val index = otherPlayer.prevIndex
        val coordOffset = otherPlayer.packedCoordOffset
        val previousCoordOffset = coordinateOffsets[index].toInt()
        if (coordOffset != previousCoordOffset) {
            // Notify the client that the player's coordinate offset has changed.
            bitBuffer.writeBits(1, 1)
            coordinateOffsets[index] = coordOffset.toByte()
            writeCoordinateOffsetBlock(previousCoordOffset, coordOffset)
        } else {
            // Notify the client that the player's coordinate offset remains the same.
            bitBuffer.writeBits(1, 0)
        }
    }

    private fun writeCoordinateOffsetBlock(lastPosition: Int, currentPosition: Int) {
        val lastX = lastPosition shr 8 and 0xFF
        val lastY = lastPosition and 0xFF
        val lastZ = lastPosition shr 16 and 0x3
        val currentX = currentPosition shr 8 and 0xFF
        val currentY = currentPosition and 0xFF
        val currentZ = currentPosition shr 16 and 0x3
        val deltaX = currentX - lastX
        val deltaY = currentY - lastY
        val deltaZ = (currentZ - lastZ) and 0x3

        if (deltaX == 0 && deltaY == 0) {
            bitBuffer.writeBits(2, 1)
            bitBuffer.writeBits(2, deltaZ)
        } else if (fastAbs(deltaX) <= 1 && fastAbs(deltaY) <= 1) {
            bitBuffer.writeBits(2, 2)
            bitBuffer.writeBits(2, deltaZ)
            bitBuffer.writeBits(3, singleTileMovementDirection(deltaX, deltaY))
        } else {
            bitBuffer.writeBits(2, 3)
            bitBuffer.writeBits(2, deltaZ)
            bitBuffer.writeBits(8, deltaX and 0xFF)
            bitBuffer.writeBits(8, deltaY and 0xFF)
        }
    }

    private fun processExternalPlayers(activePlayers: Boolean) {
        var playersToSkip = 0
        repeat(externalIndexesCount) { i ->
            val index = externalIndexes[i].toInt()
            val isInactive = activityFlag(index) == 0
            if (activePlayers == isInactive) return@repeat
            if (playersToSkip > 0) {
                playersToSkip--
                activityFlags[index] = (activityFlags[index].toInt() or 0x2).toByte()
                return@repeat
            }
            val otherPlayer: Player? = players[index]
            if (needAdd(otherPlayer)) {
                addPlayer(otherPlayer!!)
            } else if (!processExternalPlayer(otherPlayer, index)) {
                playersToSkip += writeSkipBlock(false, index, i, activePlayers)
            }
        }

        check(playersToSkip == 0) {
            "Must not have any players left to skip at the end of the process block: $activePlayers, $playersToSkip"
        }
    }

    private fun processExternalPlayer(otherPlayer: Player?, index: Int): Boolean {
        val coordinateOffset = coordinateOffset(otherPlayer)
        val cachedCoordinateOffset = coordinateOffsets[index].toInt()
        if (coordinateOffset != cachedCoordinateOffset) {
            bitBuffer.writeBits(1, 1)
            coordinateOffsets[index] = coordinateOffset.toByte()
            writeCoordinateOffsetBlock(cachedCoordinateOffset, coordinateOffset)
            return true
        }
        return false
    }

    private fun processLocalPlayers(activePlayers: Boolean) {
        var playersToSkip = 0
        repeat(localIndexesCount) { i ->
            val index = localIndexes[i].toInt()
            val isInactive = activityFlag(index) != 0
            if (activePlayers == isInactive) return@repeat
            if (playersToSkip > 0) {
                playersToSkip--
                activityFlags[index] = (activityFlags[index].toInt() or 0x2).toByte()
                return@repeat
            }
            val otherPlayer = localPlayers[index]!!
            if (needRemove(otherPlayer)) {
                removePlayer(otherPlayer)
            } else if (!processLocalPlayer(otherPlayer)) {
                playersToSkip += writeSkipBlock(true, index, i, activePlayers)
            }
        }

        check(playersToSkip == 0) {
            "Must not have any players left to skip at the end of the process block: $activePlayers, $playersToSkip"
        }
    }

    private fun writeSkipBlock(local: Boolean, index: Int, offset: Int, activePlayers: Boolean): Int {
        bitBuffer.writeBits(1, 0)
        activityFlags[index] = (activityFlags[index].toInt() or 0x2).toByte()
        val playersToSkip = when {
            local -> numOfLocalPlayersToSkip(offset + 1, activePlayers)
            else -> numOfExternalPlayersToSkip(offset + 1, activePlayers)
        }
        skipPlayers(playersToSkip)
        return playersToSkip
    }

    private fun skipPlayers(count: Int) {
        check(count >= 0) { "Skipped players count cannot be a negative integer." }
        check(count <= 0x7FF) { "Skipped players count cannot exceed 2047." }
        when {
            count == 0 -> bitBuffer.writeBits(2, 0)
            count <= 0x1F -> {
                bitBuffer.writeBits(2, 1)
                bitBuffer.writeBits(5, count)
            }

            count <= 0xFF -> {
                bitBuffer.writeBits(2, 2)
                bitBuffer.writeBits(8, count)
            }

            else -> {
                bitBuffer.writeBits(2, 3)
                bitBuffer.writeBits(11, count)
            }
        }
    }

    private fun numOfExternalPlayersToSkip(startIndex: Int, activePlayersOnly: Boolean): Int {
        var skipCount = 0
        for (i in startIndex..externalIndexesCount - 1) {
            val index = externalIndexes[i].toInt()
            val isInactive = activityFlag(index) == 0
            if (activePlayersOnly == isInactive) continue

            val otherPlayer = players[index]
            if (needAdd(otherPlayer)) break

            val coordinateOffset = coordinateOffset(otherPlayer)
            if (coordinateOffset != coordinateOffsets[index].toInt()) break

            skipCount++
        }
        return skipCount
    }

    private fun activityFlag(index: Int) = activityFlags[index].toInt() and 0x1

    private fun coordinateOffset(player: Player?): Int = player?.packedCoordOffset ?: 0

    private fun numOfLocalPlayersToSkip(startIndex: Int, activePlayersOnly: Boolean): Int {
        var skipCount = 0

        for (i in startIndex..localIndexesCount - 1) {
            val index = localIndexes[i].toInt()
            val isInactive = activityFlag(index) != 0
            if (activePlayersOnly == isInactive) continue

            val otherPlayer = localPlayers[index]!!

            if (needRemove(otherPlayer)) break
            if (otherPlayer.packedCoord != otherPlayer.previousLocation.packedCoord) break
            if (otherPlayer.flags.isUpdateRequired) break

            skipCount++
        }

        return skipCount
    }

    private fun processLocalPlayer(otherPlayer: Player): Boolean {
        val lastLocation = otherPlayer.previousLocation
        val deltaX = otherPlayer.x - lastLocation.x
        val deltaY = otherPlayer.y - lastLocation.y
        val deltaZ = otherPlayer.z - lastLocation.z
        val absX = fastAbs(deltaX)
        val absY = fastAbs(deltaY)
        val teleported = otherPlayer.teleported || absX > 2 || absY > 2
        val inRunDistance = absX == 2 || absY == 2
        val inWalkDistance = absX == 1 || absY == 1
        val needMaskUpdate = otherPlayer.flags.isUpdateRequired
        if (needMaskUpdate) writeMaskBlock(otherPlayer, false)

        // Update the coordinate offset to make sure we remain in synchronization with the client.
        coordinateOffsets[otherPlayer.index] = otherPlayer.packedCoordOffset.toByte()

        if (teleported || inRunDistance || inWalkDistance || needMaskUpdate) {
            // Notify client that there is an update coming for this player.
            bitBuffer.writeBits(1, 1)
            bitBuffer.writeBits(1, if (needMaskUpdate) 1 else 0)
        }

        when {
            teleported -> writeTeleportBlock(deltaX, deltaY, deltaZ)
            inRunDistance -> writeRunBlock(deltaX, deltaY)
            inWalkDistance -> writeWalkBlock(deltaX, deltaY)
            needMaskUpdate -> writeNoMovementBlock()
            else -> return false
        }
        return true
    }

    private fun writeNoMovementBlock() {
        bitBuffer.writeBits(2, 0)
    }

    private fun writeWalkBlock(deltaX: Int, deltaY: Int) {
        // Notify client that the player is walking.
        bitBuffer.writeBits(2, 1)
        bitBuffer.writeBits(3, singleTileMovementDirection(deltaX, deltaY))
    }

    private fun writeRunBlock(deltaX: Int, deltaY: Int) {
        // Notify client that the player is running.
        bitBuffer.writeBits(2, 2)
        bitBuffer.writeBits(4, dualTileMovementDirection(deltaX, deltaY))
    }

    private fun writeTeleportBlock(deltaX: Int, deltaY: Int, deltaZ: Int) {
        // Notify client that the player is teleporting.
        bitBuffer.writeBits(2, 3)
        if (fastAbs(deltaX) < 16 && fastAbs(deltaY) < 16) writeSmallTeleport(deltaX, deltaY, deltaZ)
        else writeLargeTeleport(deltaX, deltaY, deltaZ)
    }

    private fun writeSmallTeleport(deltaX: Int, deltaY: Int, deltaZ: Int) {
        // Notify the client that this is a small teleport block.
        bitBuffer.writeBits(1, 0)
        bitBuffer.writeBits(2, deltaZ and 0x3)
        bitBuffer.writeBits(5, deltaX and 0x1F)
        bitBuffer.writeBits(5, deltaY and 0x1F)
    }

    private fun writeLargeTeleport(deltaX: Int, deltaY: Int, deltaZ: Int) {
        // Notify the client that this is a large teleport block.
        bitBuffer.writeBits(1, 1)
        bitBuffer.writeBits(2, deltaZ and 0x3)
        bitBuffer.writeBits(14, deltaX and 0x3FFF)
        bitBuffer.writeBits(14, deltaY and 0x3FFF)
    }

    private fun writeMaskBlock(otherPlayer: Player, added: Boolean) {
        var flag = 0
        for (mask in masks) {
            if (mask.apply(localPlayer, otherPlayer, added)) {
                flag = flag or mask.playerFlag.mask
            }
        }
        // If extended flag update is required, flag the bit responsible for informing the client of it.
        if (flag >= 0xFF) {
            flag = flag or 0x4
        }
        maskBuffer.writeByte(flag)
        if (flag >= 0xFF) maskBuffer.writeByte(flag shr 8)
        for (mask in masks) {
            if (flag and mask.playerFlag.mask == 0) continue
            mask.encode(maskBuffer, localPlayer, otherPlayer)
        }
    }

    companion object {
        private const val MAX_PLAYERS = 2048
        private const val RESIZE_CHECK_INTERVAL = 10
        private const val PREFERRED_PLAYER_COUNT = 250
        private const val PREFERRED_VIEW_DISTANCE = 15

        private val masks: Array<Mask> = arrayOf(
            /*            FaceEntityMask,
                        SayMask,
                        AppearanceMask,
                        TemporaryMovementMask,
                        AnimationMask,
                        TintingMask,
                        FaceLocationMask,
                        ExactMoveMask,
                        HitMask,
                        GraphicsMask,
                        NameTagMask,
                        ChatMask,
                        MovementMask,*/
        )

        private fun singleTileMovementDirection(deltaX: Int, deltaY: Int): Int = when {
            deltaX == -1 && deltaY == -1 -> 0
            deltaX == 0 && deltaY == -1 -> 1
            deltaX == 1 && deltaY == -1 -> 2
            deltaX == -1 && deltaY == 0 -> 3
            deltaX == 1 && deltaY == 0 -> 4
            deltaX == -1 && deltaY == 1 -> 5
            deltaX == 0 && deltaY == 1 -> 6
            deltaX == 1 && deltaY == 1 -> 7
            else -> error("Both deltas must be in range of -1..1, and cannot both be 0 - $deltaX, $deltaY")
        }

        private fun dualTileMovementDirection(deltaX: Int, deltaY: Int): Int = when {
            deltaX == -2 && deltaY == -2 -> 0
            deltaX == -1 && deltaY == -2 -> 1
            deltaX == 0 && deltaY == -2 -> 2
            deltaX == 1 && deltaY == -2 -> 3
            deltaX == 2 && deltaY == -2 -> 4
            deltaX == -2 && deltaY == -1 -> 5
            deltaX == 2 && deltaY == -1 -> 6
            deltaX == -2 && deltaY == 0 -> 7
            deltaX == 2 && deltaY == 0 -> 8
            deltaX == -2 && deltaY == 1 -> 9
            deltaX == 2 && deltaY == 1 -> 10
            deltaX == -2 && deltaY == 2 -> 11
            deltaX == -1 && deltaY == 2 -> 12
            deltaX == 0 && deltaY == 2 -> 13
            deltaX == 1 && deltaY == 2 -> 14
            deltaX == 2 && deltaY == 2 -> 15
            else -> error("Both deltas must be in range of -2..2, and cannot both be 0 - $deltaX, $deltaY")
        }
    }
}
