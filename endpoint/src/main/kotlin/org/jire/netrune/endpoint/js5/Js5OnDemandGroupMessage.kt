package org.jire.netrune.endpoint.js5

import org.jire.netrune.endpoint.Js5Responses

@JvmInline
value class Js5OnDemandGroupMessage(
    val bitpack: Int
) : Js5GroupMessage {

    constructor(archive: Int, group: Int) : this(Js5Responses.bitpack(archive, group))

    override val archive get() = Js5Responses.archive(bitpack)

    override val group get() = Js5Responses.group(bitpack)

}