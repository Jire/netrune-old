package org.jire.netrune.endpoint.game

import org.openrs2.crypto.XteaKey

interface XteaRepository {

    operator fun get(region: Int): XteaKey?

    operator fun set(region: Int, key: XteaKey)

}