package org.jire.netrune.codec.osrs.game.decoder209

import org.jire.netrune.codec.EmptyPacketDecoder
import org.jire.netrune.codec.osrs.game.incoming.CloseModal

class CloseModalDecoder : EmptyPacketDecoder(7), CloseModal
