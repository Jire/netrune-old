package org.jire.netrune.endpoint.login

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import java.math.BigInteger

object Rsa {

    fun ByteBuf.toBigInteger(): BigInteger {
        val bytes = ByteBufUtil.getBytes(this, readerIndex(), readableBytes(), false)
        return BigInteger(bytes)
    }

    fun BigInteger.toByteBuf(): ByteBuf {
        return Unpooled.wrappedBuffer(toByteArray())
    }

    fun ByteBuf.rsa(exponent: BigInteger, modulus: BigInteger): ByteBuf {
        return toBigInteger().modPow(exponent, modulus).toByteBuf()
    }

}