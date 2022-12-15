package org.jire.netrune.codec.js5.outgoing

interface PrefetchedGroup : Js5OutPacket {

    val archive: Int

    val group: Int

}
