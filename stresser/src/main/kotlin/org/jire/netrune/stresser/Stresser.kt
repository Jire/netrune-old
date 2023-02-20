package org.jire.netrune.stresser

import org.jire.netrune.net.client.Client

interface Stresser : AutoCloseable {

    val target: StresserTarget

    fun connect(): Client

}