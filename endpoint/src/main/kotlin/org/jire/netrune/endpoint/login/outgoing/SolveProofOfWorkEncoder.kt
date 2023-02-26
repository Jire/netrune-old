package org.jire.netrune.endpoint.login.outgoing

import io.netty.buffer.ByteBuf
import org.jire.netrune.endpoint.VariableShortMessageEncoder
import org.openrs2.buffer.writeString
import org.openrs2.crypto.StreamCipher

object SolveProofOfWorkEncoder : VariableShortMessageEncoder<LoginResponse.SolveProofOfWork>(69) {

    override fun encode(message: LoginResponse.SolveProofOfWork, output: ByteBuf, cipher: StreamCipher) {
        output.writeByte(0)
        output.writeByte(1)
        output.writeByte(message.difficulty)
        output.writeString(message.text)
    }

}