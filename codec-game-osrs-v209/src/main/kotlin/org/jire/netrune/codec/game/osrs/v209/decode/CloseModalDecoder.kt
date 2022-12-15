package org.jire.netrune.codec.game.osrs.v209.decode

import org.jire.netrune.codec.EmptyPacketDecoder
import org.jire.netrune.codec.game.osrs.incoming.CloseModal

class CloseModalDecoder : EmptyPacketDecoder(7), CloseModal
