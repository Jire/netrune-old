package org.jire.netrune.codec.login.osrs.incoming

interface LoginXteaBlock {

    val blockLength: Int

    val loginIdentifier: LoginIdentifier

    val clientSettings: ClientSettings

    val uniqueId: UniqueId

    val sessionToken: SessionToken

    val affiliateId: Int

    val hardwareInfo: HardwareInfo

    val isJavascriptSupported: Boolean

    val cacheArchiveCrcs: CacheArchiveCrcs

}
