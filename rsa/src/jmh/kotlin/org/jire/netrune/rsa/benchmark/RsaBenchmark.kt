package org.jire.netrune.rsa.benchmark

import org.jire.netrune.rsa.Gmp
import org.jire.netrune.rsa.Rsa.publicKey
import org.jire.netrune.rsa.Rsa.toAddressable
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.PAYLOAD_SIZE
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.modulus1024
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.modulus2048
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.modulus512
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.privateKey1024
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.privateKey2048
import org.jire.netrune.rsa.benchmark.RsaBenchmarkConstants.privateKey512
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.Addressable
import java.math.BigInteger
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 3, time = 3)
@Measurement(iterations = 3, time = 3)
open class RsaBenchmark {

    private val payloadBytes = ByteArray(PAYLOAD_SIZE).apply {
        ThreadLocalRandom.current().nextBytes(this)
    }
    private val payload = BigInteger(payloadBytes)

    private val encrypted512 = payload.modPow(publicKey, modulus512)
    private val gmpEncrypted512 = encrypted512.toAddressable()
    private val encrypted1024 = payload.modPow(publicKey, modulus1024)
    private val gmpEncrypted1024 = encrypted1024.toAddressable()
    private val encrypted2048 = payload.modPow(publicKey, modulus2048)
    private val gmpEncrypted2048 = encrypted2048.toAddressable()

    private val gmpModulus512 = modulus512.toAddressable()
    private val gmpModulus1024 = modulus1024.toAddressable()
    private val gmpModulus2048 = modulus2048.toAddressable()

    private val gmpPrivateKey512 = privateKey512.toAddressable()
    private val gmpPrivateKey1024 = privateKey1024.toAddressable()
    private val gmpPrivateKey2048 = privateKey2048.toAddressable()

    private fun decodeBigInteger(
        blackhole: Blackhole,
        encrypted: BigInteger, privateKey: BigInteger, modulus: BigInteger
    ) {
        val decrypted = encrypted.modPow(privateKey, modulus)
        blackhole.consume(decrypted)
    }

    @Benchmark
    fun decodeBigInteger0512(blackhole: Blackhole) = decodeBigInteger(
        blackhole,
        encrypted512, privateKey512, modulus512
    )

    @Benchmark
    fun decodeBigInteger1024(blackhole: Blackhole) = decodeBigInteger(
        blackhole,
        encrypted1024, privateKey1024, modulus1024
    )

    @Benchmark
    fun decodeBigInteger2048(blackhole: Blackhole) = decodeBigInteger(
        blackhole,
        encrypted2048, privateKey2048, modulus2048
    )

    private fun decodeGmp(
        blackhole: Blackhole,
        gmpEncrypted: Addressable, gmpPrivateKey: Addressable, gmpModulus: Addressable
    ) {
        val decrypted = Gmp.mpz_t()
        Gmp.mpz_init(decrypted)

        Gmp.mpz_powm(decrypted, gmpEncrypted, gmpPrivateKey, gmpModulus)

        Gmp.mpz_clear(decrypted)
    }

    @Benchmark
    fun decodeGmp0512(blackhole: Blackhole) = decodeGmp(
        blackhole,
        gmpEncrypted512, gmpPrivateKey512, gmpModulus512
    )

    @Benchmark
    fun decodeGmp1024(blackhole: Blackhole) = decodeGmp(
        blackhole,
        gmpEncrypted1024, gmpPrivateKey1024, gmpModulus1024
    )

    @Benchmark
    fun decodeGmp2048(blackhole: Blackhole) = decodeGmp(
        blackhole,
        gmpEncrypted2048, gmpPrivateKey2048, gmpModulus2048
    )

}
