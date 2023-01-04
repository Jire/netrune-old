package org.jire.netrune.jmh.rsa

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.math.BigInteger
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 0)
@Measurement(iterations = 1, time = 5)
open class RsaBenchmark {

    private val privateExponent = BigInteger(
        "8r2hl97wkw1tqrvianejtja0vwb8v2cwlu4f3nuhacc4auh4sln4m5y4wcu0t41267wopyvua3wozis2vlpxoka060lsv9q9uf3geecofzi5nvzcduvwhrrc9eqsq5oiy3m9ttgf0mmc1z31q0xd4vhthzchaqi1copcekdyujbjnprya6neuvkhvm5rf1s299xbgin3gwiu9l4fuvzoz821tm6j6ac7rym2eisns8vcoycld0a65tpp4fvmemgte0h6gvkkmd882lawa061bgmqf7yr27ijszzhlkowcetkdbwhg8c4rbqhupxbvvdvk83302w0ijbxcukrglzu9jk3nf6aizuzk5fvi8ww7a245h5mg4lwtlx75ulzek0sh2lrab2seit5",
        36
    )
    private val privateModulus = BigInteger(
        "zu24jygdg2scwtf37mo2d82fhn0jpflhml6y5sfvldtokjkbinuvtdk4qoyfenjye2a4drui3o65o0g5f831h69j2yu3ilidrwe3zdcxvpdyng4aylmyom44bi2eppab7awucm3imtl810ta6r8swfjn38gzfo6ma8w2jx73eott735va4rpvvplmzovx0svcvn92he1mrdg5ovkmqyzi61ylw96ztuf2vfkvspi4t6253jsoh0pckwvy1bncd1nknhn7rg53aurs7m754mnyz23qepv43l7qe2jizy1u75buku3iovw9vt3nzwujbmov5p8u7vbezzryo1v181ca90yo88saxcolurhu2txdhw7aq7iwka93rzazmc4agsljdazehidn8j5",
        36
    )

    private val publicExponent = BigInteger("65537")
    private val publicModulus = BigInteger(
        "9bd0220dd8ba90fb50d00115bf9616c936013e54d0271cb5109e1f39b9fc91483633e6bbe8aeb1bf4175520973b24adc60598eaa848bf28b0dba4ab5a2fc984c8c1a5e059e98170870ac78b6951bd5a090c503509d1f1d19e17a45edafbb632fe490cec99825a9093e2cdd6aa39fc6f095845ae722ff41e3ac1729f20cc9d92b491bf5d0da7ec3fe4ca12474a018696d4923fda322657209822b923f5698e209af0343a3e970360167365a0087260f9d9de24e62bf6a981fe4077cd03e44df7ff3b4b8afebb75c1ad139dca9771aba56b0a2a1c541772f5e692cf3d0b1be2f5855ddedb3c40b4daa89b2f137d1b262f1902dc497e72328683aaa626932efcff1",
        16
    )

    private lateinit var msg: BigInteger

    @Setup(Level.Invocation)
    fun setup() {
        msg = BigInteger(ByteArray(500).apply {
            ThreadLocalRandom.current().nextBytes(this)
        }).modPow(publicExponent, publicModulus)
    }

    @Benchmark
    fun decodeBigInteger(blackhole: Blackhole) {
        val decrypted = msg.modPow(privateExponent, privateModulus)
        blackhole.consume(decrypted)
    }

    @Benchmark
    fun decodeCipher(blackhole: Blackhole) {

    }

}
