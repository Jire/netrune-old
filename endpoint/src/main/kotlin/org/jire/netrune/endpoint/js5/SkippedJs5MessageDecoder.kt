package org.jire.netrune.endpoint.js5

import org.jire.netrune.endpoint.SkippedMessageDecoder

abstract class SkippedJs5MessageDecoder<M : Js5DecodeMessage>(
    message: M
) : SkippedMessageDecoder<M>(3, message)