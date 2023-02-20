package org.jire.netrune.update317

interface AvatarExtensions {

    var flags: Int

    fun flag(extension: AvatarExtension) {
        flags = flag(flags, extension)
    }

    fun isFlagged(extension: AvatarExtension) = isFlagged(flags, extension)

    companion object {
        fun flag(flags: Int, flagBitMask: Int) = flags or flagBitMask
        fun flag(flags: Int, extension: AvatarExtension) = flag(flags, extension.flagBitMask)

        fun isFlagged(flags: Int, flagBitMask: Int) = (flags and flagBitMask) != 0
        fun isFlagged(flags: Int, extension: AvatarExtension) = isFlagged(flags, extension.flagBitMask)
    }

}