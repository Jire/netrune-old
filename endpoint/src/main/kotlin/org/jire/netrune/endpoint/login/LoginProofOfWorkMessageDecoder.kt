package org.jire.netrune.endpoint.login

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.VariableShortMessageDecoder
import org.openrs2.crypto.StreamCipher

object LoginProofOfWorkMessageDecoder : VariableShortMessageDecoder<LoginProofOfWorkMessage>() {

    override fun decode(input: ByteBuf, opcode: Int, cipher: StreamCipher): LoginProofOfWorkMessage {
        val nonce = input.readLong()
        return LoginProofOfWorkMessage(nonce)
    }

}