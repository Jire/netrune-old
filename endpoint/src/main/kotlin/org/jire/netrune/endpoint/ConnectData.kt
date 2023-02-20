package org.jire.netrune.endpoint

import io.netty.buffer.ByteBuf

data class ConnectData(
    val version: Int,
    val subVersion: Int,
    val clientType: Int,
    val jxType: Int,

    val encryptionType: Int,
    val encryptionLength: Int,
    val encryptionData: ByteBuf
)
