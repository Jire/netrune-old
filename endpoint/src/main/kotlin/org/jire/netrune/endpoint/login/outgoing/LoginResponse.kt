package org.jire.netrune.endpoint.login.outgoing

import org.jire.netrune.endpoint.OutgoingMessage

sealed interface LoginResponse : OutgoingMessage {

    data class SolveProofOfWork(
        val difficulty: Int,
        val text: String
    ) : LoginResponse

}