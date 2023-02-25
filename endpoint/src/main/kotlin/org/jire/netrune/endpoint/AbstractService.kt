package org.jire.netrune.endpoint

abstract class AbstractService(
    private val decodersArrayLength: Int
) : Service {

    private val decoders: Array<MessageDecoder<*>?> = arrayOfNulls(decodersArrayLength)

    override fun getDecoder(opcode: Int): MessageDecoder<*>? {
        if (opcode < 0 || opcode >= decodersArrayLength)
            throw IllegalArgumentException("Opcode out of bounds: $opcode")

        return decoders[opcode]
    }

    override fun setDecoder(opcode: Int, decoder: MessageDecoder<*>) {
        decoders[opcode] = decoder
    }

}