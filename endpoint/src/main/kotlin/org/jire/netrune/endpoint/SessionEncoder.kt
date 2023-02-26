package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class SessionEncoder(
    private val session: Session
) : MessageToByteEncoder<OutgoingMessage>(OutgoingMessage::class.java) {

    override fun encode(ctx: ChannelHandlerContext, message: OutgoingMessage, output: ByteBuf) {
        val messageClass = message.javaClass
        val encoder = session.service.getEncoder(messageClass)
            ?: error("No encoder found for message type: $messageClass")

        val cipher = session.encodeCipher

        output.writeByte(encoder.opcode + cipher.nextInt())

        val lengthWriterIndex = output.writerIndex()
        encoder.writeLengthPlaceholder(output)

        val payloadWriterIndex = output.writerIndex()
        encoder.encode(message, output, cipher)

        val payloadLength = output.writerIndex() - payloadWriterIndex
        encoder.setLength(output, lengthWriterIndex, payloadLength)
    }

}