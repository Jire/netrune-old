package org.jire.netrune.codec.login.osrs.incoming

interface Login : OsrsLoginInPacket {

    val reconnecting: Boolean

    val clientBuild: Int

    val clientSubBuild: Int

    val clientType: Int

    val rsaBlock: LoginRsaBlock

    val xteaBlock: LoginXteaBlock

}
