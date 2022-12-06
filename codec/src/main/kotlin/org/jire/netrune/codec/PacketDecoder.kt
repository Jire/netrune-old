package org.jire.netrune.codec

interface PacketDecoder : BufferDecoder {

    val opcode: Int

    val size: Int

}
