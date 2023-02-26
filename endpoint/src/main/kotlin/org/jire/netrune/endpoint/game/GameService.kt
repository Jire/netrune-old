package org.jire.netrune.endpoint.game

import io.netty.channel.ChannelHandlerContext
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.IncomingMessage
import org.jire.netrune.endpoint.Session

class GameService : AbstractService(256) {

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: IncomingMessage) {

    }

    init {
        // TODO
    }

}