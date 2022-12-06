package org.jire.netrune.codec

abstract class VarShortPacketDecoder(
    opcode: Int,
    override var size: Int = -2
) : SteppedPacketDecoder(opcode, size) {

    init {
        steps.completable {
            if (input.has(2)) {
                size = input.uShort()
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
