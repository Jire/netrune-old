package org.jire.netrune.stresser

data class DefaultStresserTarget(
    override val host: String,
    override val port: Int = StresserTarget.DEFAULT_PORT
) : StresserTarget
