package org.jire.netrune.stackgpi

import io.netty.buffer.ByteBuf
import io.netty.buffer.DefaultByteBufHolder
import org.jire.netrune.buffer.netty4.Netty4WritableBuffer

class NettyBuf(override val byteBuf: ByteBuf) :
    DefaultByteBufHolder(byteBuf),
    Netty4WritableBuffer