package org.jire.netrune.endpoint.js5

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import org.jire.netrune.endpoint.js5.Js5GroupRepository.Companion.bitpack
import org.openrs2.buffer.use
import org.openrs2.cache.*
import org.openrs2.cache.Store.Companion.ARCHIVESET
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.min
import kotlin.math.pow

class Openrs2Js5GroupRepository(
    private val store: Store,
    private val masterIndex: Js5MasterIndex
) : Js5GroupRepository {

    private val map: Int2ObjectMap<ByteBuf> = Int2ObjectOpenHashMap(2.toDouble().pow(17).toInt())

    override fun get(bitpack: Int): ByteBuf? = map[bitpack]

    override fun load() {
        encodeMasterIndex(masterIndex)

        for (archive in store.list()) {
            encodeArchive(store, archive)
        }

        logger.info("Loaded {} JS5 responses", map.size)
    }

    private fun encodeMasterIndex(masterIndex: Js5MasterIndex) {
        Unpooled.directBuffer().use { uncompressed ->
            masterIndex.write(uncompressed)

            val data = Js5Compression.compress(uncompressed, Js5CompressionType.UNCOMPRESSED)
            encodeGroup(ARCHIVESET, ARCHIVESET, data)
        }
    }

    private fun encodeArchive(store: Store, archive: Int) {
        for (group in store.list(archive)) {
            if (archive == ARCHIVESET && group == ARCHIVESET) continue

            store.read(archive, group).use { data ->
                if (archive != ARCHIVESET) {
                    VersionTrailer.strip(data)
                }

                encodeGroup(archive, group, data)
            }
        }
    }

    private fun encodeGroup(archive: Int, group: Int, data: ByteBuf) {
        val response = Unpooled.directBuffer()
            .writeByte(archive)
            .writeShort(group)
            .writeByte(data.readUnsignedByte().toInt()) // compression
            .writeBytes(data, min(data.readableBytes(), BYTES_BEFORE_BLOCK))
        while (data.isReadable) {
            response.writeByte(0xFF)
            response.writeBytes(data, min(data.readableBytes(), BYTES_AFTER_BLOCK))
        }

        val bitpack = bitpack(archive, group)
        map[bitpack] = response
    }

    private companion object {

        private const val BLOCK_SIZE = 512
        private const val BLOCK_HEADER_SIZE = 1 + 2 + 1
        private const val BLOCK_DELIMITER_SIZE = 1
        private const val BYTES_BEFORE_BLOCK = BLOCK_SIZE - BLOCK_HEADER_SIZE
        private const val BYTES_AFTER_BLOCK = BLOCK_SIZE - BLOCK_DELIMITER_SIZE

        private val logger: Logger = LoggerFactory.getLogger(Js5GroupRepository::class.java)

    }

}