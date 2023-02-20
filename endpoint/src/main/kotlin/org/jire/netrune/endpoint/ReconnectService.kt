package org.jire.netrune.endpoint

class ReconnectService : ConnectService() {

    override fun service(connectData: ConnectData) = GameService(connectData)

}