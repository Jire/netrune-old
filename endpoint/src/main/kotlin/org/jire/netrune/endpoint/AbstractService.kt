package org.jire.netrune.endpoint

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

abstract class AbstractService(
    private val decodersArrayLength: Int
) : Service {

    private val decoders: Array<MessageDecoder<*>?> = arrayOfNulls(decodersArrayLength)
    private val encoders: MutableMap<Class<*>, MessageEncoder<*>> = Object2ObjectOpenHashMap()

    override fun getDecoder(opcode: Int): MessageDecoder<*>? {
        if (opcode < 0 || opcode >= decodersArrayLength)
            throw IllegalArgumentException("Opcode out of bounds: $opcode")

        return decoders[opcode]
    }

    override fun setDecoder(opcode: Int, decoder: MessageDecoder<*>) {
        decoders[opcode] = decoder
    }

    override fun <M : OutgoingMessage> getEncoder(type: Class<M>): MessageEncoder<M>? {
        @Suppress("UNCHECKED_CAST")
        return encoders[type] as? MessageEncoder<M>
    }

    override fun <M : OutgoingMessage> setEncoder(type: Class<M>, encoder: MessageEncoder<M>) {
        encoders[type] = encoder
    }

}