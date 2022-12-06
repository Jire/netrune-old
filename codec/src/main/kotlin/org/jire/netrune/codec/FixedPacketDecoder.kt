package org.jire.netrune.codec

abstract class FixedPacketDecoder(
    opcode: Int, size: Int
) : SteppedPacketDecoder(opcode, size) {

    init {
        steps.completable {
            if (input.has(size)) {
                done()
            }
        }
    }

}
