package org.jire.netrune.codec.js5.incoming

interface FetchGroup : Js5InPacket {

    val archive: Int

    val group: Int

}
