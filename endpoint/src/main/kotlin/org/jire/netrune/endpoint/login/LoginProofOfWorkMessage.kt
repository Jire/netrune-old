package org.jire.netrune.endpoint.login

@JvmInline
value class LoginProofOfWorkMessage(
    val nonce: Long
) : LoginDecodeMessage