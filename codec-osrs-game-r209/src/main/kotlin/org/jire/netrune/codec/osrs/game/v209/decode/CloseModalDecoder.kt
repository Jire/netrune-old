package org.jire.netrune.codec.osrs.game.v209.decode

import org.jire.netrune.codec.EmptyPacketDecoder
import org.jire.netrune.codec.osrs.game.incoming.CloseModal

class CloseModalDecoder : EmptyPacketDecoder(7), CloseModal
