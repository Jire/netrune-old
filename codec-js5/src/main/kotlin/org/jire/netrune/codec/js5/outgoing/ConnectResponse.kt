package org.jire.netrune.codec.js5.outgoing

sealed interface ConnectResponse : Js5OutPacket {

    interface Connected : ConnectResponse

    interface ClientOutOfDate : ConnectResponse

    interface ServerFull : ConnectResponse

    interface RetryLater : ConnectResponse

}
