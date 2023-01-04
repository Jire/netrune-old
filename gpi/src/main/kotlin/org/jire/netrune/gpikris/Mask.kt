package org.jire.netrune.gpikris

import io.netty.buffer.ByteBuf

interface Mask {

    val playerFlag: Flag

    fun apply(observer: Player, target: Player, added: Boolean): Boolean

    fun encode(buffer: ByteBuf, observer: Player, target: Player)

}
