package org.jire.netrune.endpoint

abstract class AbstractMessageEncoder<M : OutgoingMessage>(
    override val opcode: Int
) : MessageEncoder<M>