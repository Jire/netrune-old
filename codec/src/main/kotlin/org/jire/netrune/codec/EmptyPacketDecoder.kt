package org.jire.netrune.codec

abstract class EmptyPacketDecoder(
    opcode: Int
) : SteppedPacketDecoder(opcode, 0)
