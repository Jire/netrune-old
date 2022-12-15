package org.jire.netrune.codec.js5.outgoing

interface FetchedGroup : Js5OutPacket {

    val archive: Int

    val group: Int

}
