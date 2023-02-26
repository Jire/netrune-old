package org.jire.netrune.endpoint.game

data class Xtea(
    val mapsquare: Int,
    val key: IntArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as Xtea

        if (mapsquare != other.mapsquare) return false
        if (!key.contentEquals(other.key)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + mapsquare
        result = 31 * result + key.contentHashCode()
        return result
    }

}
