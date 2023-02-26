package org.jire.netrune.endpoint.init.outgoing

sealed interface InitLoginResponse : InitResponse {

    data class SessionKey(val key: Long) : InitLoginResponse

}