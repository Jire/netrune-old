package org.jire.netrune.endpoint.login.incoming

import io.netty.buffer.ByteBuf

data class LoginConnect(
    val reconnect: Boolean,

    val buildMajor: Int,
    val buildMinor: Int,

    val clientType: Int,
    val platformType: Int,

    val secureBlockType: Int,
    val secureBlockLength: Int,
    val secureBlockData: ByteBuf,

    val xteaBlockLength: Int,
    val xteaBlockData: ByteBuf
) : LoginIncomingMessage