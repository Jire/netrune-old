package org.jire.netrune.codec.js5.osrs.decode

import org.jire.netrune.codec.FixedPacketDecoder

abstract class Js5PacketDecoder(
    opcode: Int
) : FixedPacketDecoder(opcode, 3)
