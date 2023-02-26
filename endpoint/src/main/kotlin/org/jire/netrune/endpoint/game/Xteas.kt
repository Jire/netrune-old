package org.jire.netrune.endpoint.game

import com.google.gson.Gson
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import java.nio.file.Path
import kotlin.io.path.bufferedReader

object Xteas {

    private val map: Int2ObjectMap<Xtea> = Int2ObjectOpenHashMap()

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
                map[xtea.mapsquare] = xtea
            }
        }
    }

    @JvmStatic
    operator fun get(region: Int): Xtea? = map.get(region)

}