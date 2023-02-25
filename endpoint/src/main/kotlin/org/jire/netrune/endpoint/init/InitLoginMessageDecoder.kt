package org.jire.netrune.endpoint.init

import org.jire.netrune.endpoint.EmptyMessageDecoder

object InitLoginMessageDecoder : EmptyMessageDecoder<InitLoginMessage>(InitLoginMessage)