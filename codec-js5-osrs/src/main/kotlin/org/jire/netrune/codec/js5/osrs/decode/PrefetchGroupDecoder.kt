package org.jire.netrune.codec.js5.osrs.decode

import org.jire.netrune.codec.js5.incoming.FetchGroup

class PrefetchGroupDecoder : Js5PacketDecoder(0), FetchGroup {

    override val archive by uByte

    override val group by uShort

}
