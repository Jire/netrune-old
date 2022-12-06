package org.jire.netrune.codec

abstract class VarBytePacketDecoder(
    opcode: Int,
    override var size: Int = -1
) : SteppedPacketDecoder(opcode, size) {

    init {
        steps.completable {
            if (input.has(1)) {
                size = input.uByte()
                done()
            }
        }
        steps.completable {
            if (input.has(size)) {
                done()
            }
        }
    }

}
