package org.jire.netrune.codec.js5.incoming

interface PrefetchGroup : Js5InPacket {

    val archive: Int

    val group: Int

}
