package org.jire.netrune.codec.login.osrs.incoming

interface ProofOfWork : OsrsLoginInPacket {

    val nonce: Long

}
