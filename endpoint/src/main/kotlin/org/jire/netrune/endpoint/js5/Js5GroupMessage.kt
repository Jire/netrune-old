package org.jire.netrune.endpoint.js5

interface Js5GroupMessage : Js5DecodeMessage {

    val archive: Int
    val group: Int

}