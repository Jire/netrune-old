package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.DecoderException

class SessionDecoder(
    private val session: Session
) : ByteToMessageDecoder() {

    private enum class State {
        OPCODE,
        LENGTH,
        PAYLOAD
    }

    private var state = State.OPCODE
    private var opcode = -1
    private lateinit var decoder: MessageDecoder<*>
    private var length = 0

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        if (state == State.OPCODE) {
            val session = session

            opcode = (input.readUnsignedByte().toInt() - session.decodeCipher.nextInt()) and 0xFF
            decoder = session.service.getDecoder(opcode)
                ?: throw DecoderException("Unsupported opcode: $opcode")

            state = State.LENGTH
        }

        if (state == State.LENGTH) {
            if (!decoder.isLengthReadable(input)) return

            length = decoder.readLength(input)

            state = State.PAYLOAD
        }

        if (state == State.PAYLOAD) {
            if (!input.isReadable(length)) return

            val payload = input.readSlice(length)

            val message = decoder.decode(payload, opcode, session.decodeCipher)

            out += message

            state = State.OPCODE
        }
    }

}
