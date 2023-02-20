package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.DecoderException

class InitService(
    private val js5Responses: Js5Responses
) : Service {

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>): Service {
        if (!input.isReadable) return this

        return when (val opcode = input.readUnsignedByte().toInt()) {
            SESSION_OPCODE -> SessionService
            JS5_OPCODE -> Js5Service(js5Responses)
            LOGIN_OPCODE -> ConnectService()
            RECONNECT_OPCODE -> ReconnectService()
            PROOF_OF_WORK_OPCODE -> ProofOfWorkService(null)
            else -> throw DecoderException("Unsupported service opcode: $opcode")
        }
    }

    private companion object {
        private const val SESSION_OPCODE = 14
        private const val JS5_OPCODE = 15
        private const val LOGIN_OPCODE = 16
        private const val RECONNECT_OPCODE = 18
        private const val PROOF_OF_WORK_OPCODE = 19
    }

}