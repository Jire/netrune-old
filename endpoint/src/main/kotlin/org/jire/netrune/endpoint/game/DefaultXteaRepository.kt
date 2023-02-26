package org.jire.netrune.endpoint.game

import com.google.gson.Gson
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import org.openrs2.crypto.XteaKey
import java.nio.file.Path
import kotlin.io.path.bufferedReader

object DefaultXteaRepository : XteaRepository {

    private val map: Int2ObjectMap<XteaKey> = Int2ObjectOpenHashMap()

    @JvmStatic
    @JvmOverloads
    fun load(
        path: Path = Path.of("data", "xteas.json"),
        gson: Gson = Gson()
    ) {
        path.bufferedReader().use { reader ->
            val xteas = gson.fromJson(reader, Array<Xtea?>::class.java)
            for (xtea in xteas) {
                xtea ?: continue
                set(xtea.mapsquare, XteaKey.fromIntArray(xtea.key))
            }
        }
    }

    override operator fun get(region: Int): XteaKey? = map.get(region)

    override fun set(region: Int, key: XteaKey) {
        map[region] = key
    }

}