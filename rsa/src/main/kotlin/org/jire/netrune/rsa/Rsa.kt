package org.jire.netrune.rsa

import java.lang.foreign.Addressable
import java.lang.foreign.MemorySegment
import java.lang.foreign.MemorySession
import java.lang.foreign.ValueLayout
import java.math.BigInteger
import java.security.SecureRandom
import java.util.*

object Rsa {

    const val DEFAULT_PUBLIC_KEY = "65537"
    const val DEFAULT_PUBLIC_KEY_RADIX = 10

    val publicKey = BigInteger(DEFAULT_PUBLIC_KEY, DEFAULT_PUBLIC_KEY_RADIX)

    @JvmStatic
    fun main(args: Array<String>) {
        val (modulus, privateKey) = generateModulusAndPrivateKey(bitLength = 1024)
        println(modulus.toString(Character.MAX_RADIX))
        println(privateKey.toString(Character.MAX_RADIX))
    }

    fun generateModulusAndPrivateKey(
        bitLength: Int = 1024,
        publicKey: BigInteger = Rsa.publicKey,
        random: Random = SecureRandom()
    ): Pair<BigInteger, BigInteger> {
        val primeBitLength = bitLength / 2
        val p = BigInteger.probablePrime(primeBitLength, random)
        val q = BigInteger.probablePrime(primeBitLength, random)

        val modulus = p.multiply(q)

        val phi = p.subtract(BigInteger.ONE)
            .multiply(q.subtract(BigInteger.ONE))
        val privateKey = publicKey.modInverse(phi)

        return modulus to privateKey
    }

    fun ByteArray.toAddressable(memorySession: MemorySession = MemorySession.global()): Addressable {
        val size = size
        val address = memorySession.allocate(size.toLong())
        MemorySegment.copy(this, 0, address, ValueLayout.JAVA_BYTE, 0, size)
        return address
    }

    fun BigInteger.toAddressable(memorySession: MemorySession = MemorySession.global()): Addressable {
        val rop = Gmp.mpz_t()
        Gmp.mpz_init(rop)

        val byteArray = toByteArray()
        val data = byteArray.toAddressable(memorySession)

        Gmp.mpz_import(rop, byteArray.size.toLong(), -1, 1, 1, 0, data)
        return rop
    }

}