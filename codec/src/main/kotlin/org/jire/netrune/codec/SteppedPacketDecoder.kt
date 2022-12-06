package org.jire.netrune.codec

abstract class SteppedPacketDecoder(
    override val opcode: Int,
    override val size: Int
) : SteppedBufferDecoder(),
    PacketDecoder
