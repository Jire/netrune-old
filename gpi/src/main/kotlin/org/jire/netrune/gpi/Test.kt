package org.jire.netrune.gpi

import org.jire.netrune.gpi.player.Player

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val position = object : Position {
            override val y = 0
            override val x = 3200
            override val z = 3200
        }
        val player: Player = Player(
            1, position, position,
        )
        player.extensions.say.update("Hello, world!")
    }

}
