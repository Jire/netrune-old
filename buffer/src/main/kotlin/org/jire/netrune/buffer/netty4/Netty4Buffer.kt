package org.jire.netrune.buffer.netty4

import io.netty.buffer.ByteBuf
import org.jire.netrune.buffer.Buffer

interface Netty4Buffer : Buffer {

    val byteBuf: ByteBuf

}
