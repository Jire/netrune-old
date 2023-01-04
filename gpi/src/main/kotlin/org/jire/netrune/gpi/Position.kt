package org.jire.netrune.gpi

interface Position {

    val y: Int
    val x: Int
    val z: Int

    operator fun component1() = y
    operator fun component2() = x
    operator fun component3() = z

}
