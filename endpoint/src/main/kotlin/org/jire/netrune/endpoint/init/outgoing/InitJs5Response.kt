package org.jire.netrune.endpoint.init.outgoing

sealed interface InitJs5Response : InitResponse {

    object Proceed : InitJs5Response
    object ClientOutOfDate : InitJs5Response
    object ServerFull : InitJs5Response
    object IpLimit : InitJs5Response

}