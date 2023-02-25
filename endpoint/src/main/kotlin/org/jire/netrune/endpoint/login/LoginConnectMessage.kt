package org.jire.netrune.endpoint.login

import io.netty.buffer.ByteBuf

data class LoginConnectMessage(
    val reconnect: Boolean,

    val version: Int,
    val subVersion: Int,

    val clientType: Int,
    val jxType: Int,

    val encryptType: Int,
    val encryptedLength: Int,
    val encryptedData: ByteBuf,

    val xteaLength: Int,
    val xteaData: ByteBuf
) : LoginDecodeMessage