package org.jire.netrune.codec.js5.osrs.decode

import org.jire.netrune.codec.js5.incoming.SetKey

class SetKeyDecoder : Js5PacketDecoder(4), SetKey {

    override val key by uByte

    private val skipped by skipBytes(2)

}
