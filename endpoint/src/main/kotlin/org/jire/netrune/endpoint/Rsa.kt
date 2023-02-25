package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import java.math.BigInteger

object Rsa {

    private val EXPONENT = BigInteger(
        "8r2hl97wkw1tqrvianejtja0vwb8v2cwlu4f3nuhacc4auh4sln4m5y4wcu0t41267wopyvua3wozis2vlpxoka060lsv9q9uf3geecofzi5nvzcduvwhrrc9eqsq5oiy3m9ttgf0mmc1z31q0xd4vhthzchaqi1copcekdyujbjnprya6neuvkhvm5rf1s299xbgin3gwiu9l4fuvzoz821tm6j6ac7rym2eisns8vcoycld0a65tpp4fvmemgte0h6gvkkmd882lawa061bgmqf7yr27ijszzhlkowcetkdbwhg8c4rbqhupxbvvdvk83302w0ijbxcukrglzu9jk3nf6aizuzk5fvi8ww7a245h5mg4lwtlx75ulzek0sh2lrab2seit5",
        36
    )
    private val MODULUS = BigInteger(
        "zu24jygdg2scwtf37mo2d82fhn0jpflhml6y5sfvldtokjkbinuvtdk4qoyfenjye2a4drui3o65o0g5f831h69j2yu3ilidrwe3zdcxvpdyng4aylmyom44bi2eppab7awucm3imtl810ta6r8swfjn38gzfo6ma8w2jx73eott735va4rpvvplmzovx0svcvn92he1mrdg5ovkmqyzi61ylw96ztuf2vfkvspi4t6253jsoh0pckwvy1bncd1nknhn7rg53aurs7m754mnyz23qepv43l7qe2jizy1u75buku3iovw9vt3nzwujbmov5p8u7vbezzryo1v181ca90yo88saxcolurhu2txdhw7aq7iwka93rzazmc4agsljdazehidn8j5",
        36
    )

    fun ByteBuf.toBigInteger(): BigInteger {
        val bytes = ByteBufUtil.getBytes(this, readerIndex(), readableBytes(), false)
        return BigInteger(bytes)
    }

    fun BigInteger.toByteBuf(): ByteBuf {
        return Unpooled.wrappedBuffer(toByteArray())
    }

    fun ByteBuf.rsa(): ByteBuf {
        return toBigInteger().modPow(EXPONENT, MODULUS).toByteBuf()
    }

}