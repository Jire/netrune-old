package org.jire.netrune.endpoint.login.incoming

@JvmInline
value class LoginProofOfWork(
    val nonce: Long
) : LoginIncomingMessage