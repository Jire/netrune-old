package org.jire.netrune.endpoint.init.outgoing

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.FixedMessageEncoder
import org.openrs2.crypto.StreamCipher

object LoginSessionKeyResponseEncoder : FixedMessageEncoder<InitLoginResponse.SessionKey>(
    0,
    8
) {

    override fun encode(message: InitLoginResponse.SessionKey, output: ByteBuf, cipher: StreamCipher) {
        output.writeLong(message.key)
    }

}