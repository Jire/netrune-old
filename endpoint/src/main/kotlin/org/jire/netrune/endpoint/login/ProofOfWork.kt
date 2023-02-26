package org.jire.netrune.endpoint.login

import com.google.common.hash.Hashing
import java.math.BigInteger
import java.util.concurrent.ThreadLocalRandom

object ProofOfWork {

    @JvmStatic
    @JvmOverloads
    fun generateText(
        timestamp: Long = System.currentTimeMillis(),
        worldId: Int = 1
    ): String {
        val randomData = ByteArray(495)
        ThreadLocalRandom.current().nextBytes(randomData)

        return timestamp.toString(16) +
                worldId.toString(16) +
                BigInteger(randomData).toString(16)
    }

    fun validate(
        proofDifficulty: Int, proofText: String,
        nonce: Long
    ): Boolean {
        val str = 1.toString(16) +
                proofDifficulty.toString(16) +
                proofText +
                nonce.toString(16)

        @Suppress("UnstableApiUsage") val hash = Hashing.sha256().hashBytes(str.toByteArray()).asBytes()
        val trailingBits = hash.countTrailingZeroBits()

        return trailingBits >= proofDifficulty
    }

    private fun ByteArray.countTrailingZeroBits(): Int {
        var bits = 0
        for (b in this) {
            val n = b.countTrailingZeroBits()
            bits += n
            if (n != 8) break
        }
        return bits
    }

}