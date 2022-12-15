package org.jire.netrune.codec.js5.osrs.decode

abstract class SkippedJs5PacketDecoder(
    opcode: Int
) : Js5PacketDecoder(opcode) {

    private val skipped by skipBytes(3)

}
