package org.jire.netrune.endpoint.js5

import org.jire.netrune.endpoint.Js5Responses

@JvmInline
value class Js5GroupMessage(
    val bitpack: Int
) : Js5DecodeMessage {

    constructor(archive: Int, group: Int) : this(Js5Responses.bitpack(archive, group))

    val archive get() = Js5Responses.archive(bitpack)

    val group get() = Js5Responses.group(bitpack)

}