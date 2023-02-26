package org.jire.netrune.endpoint.js5.incoming

import org.jire.netrune.endpoint.SkippedMessageDecoder

abstract class SkippedJs5Decoder<M : Js5IncomingMessage>(
    message: M
) : SkippedMessageDecoder<M>(3, message)