package org.jire.netrune.codec.game.osrs

@JvmInline
value class Widget(val widgetId: Int) {

    constructor(
        interfaceId: Int,
        componentId: Int
    ) : this(
        (componentId and 0xFFFF)
                or ((interfaceId and 0xFFFF) shl 16)
    )

    val interfaceId get() = widgetId ushr 16

    val componentId get() = widgetId and 0xFFFF

}
