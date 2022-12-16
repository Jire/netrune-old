package org.jire.netrune.codec.login.osrs.incoming

interface CacheArchiveCrcs {

    operator fun get(archiveId: Int): Int

}
