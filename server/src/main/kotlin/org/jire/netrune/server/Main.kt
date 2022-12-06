package org.jire.netrune.server

object Main {

    private const val PORT = 43594

    @JvmStatic
    fun main(args: Array<String>) {
        Server()
            .bind(PORT)
    }

}
