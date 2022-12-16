package org.jire.netrune.codec.login.osrs.incoming

interface ClientSettings {

    val flags: Int

    val isLowMemory: Boolean get() = (flags and 1) == 1

    val isResizable get() = ((flags ushr 1) and 1) == 1

    val width: Int

    val height: Int

}
