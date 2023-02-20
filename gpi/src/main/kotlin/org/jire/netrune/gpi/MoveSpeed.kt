package org.jire.netrune.gpi

enum class MoveSpeed(
    val speed: Byte
) {

    STATIONARY(-1),
    CRAWL(0),
    WALK(1),
    RUN(2)

}