package org.jire.netrune.endpoint.init

import io.netty.channel.ChannelHandlerContext
import io.netty.util.internal.ThreadLocalRandom
import org.jire.netrune.endpoint.AbstractService
import org.jire.netrune.endpoint.DecodeMessage
import org.jire.netrune.endpoint.Js5Responses
import org.jire.netrune.endpoint.Session
import org.jire.netrune.endpoint.js5.Js5Service
import org.jire.netrune.endpoint.login.LoginService
import java.util.concurrent.Executor

class InitService(
    private val js5Responses: Js5Responses,
    private val executor: Executor
) : AbstractService(32) {

    override fun handle(session: Session, ctx: ChannelHandlerContext, message: DecodeMessage) {
        when (message) {
            is InitLoginMessage -> {
                val serverSeed = ThreadLocalRandom.current().nextLong()
                session.serverSeed = serverSeed

                val service = LoginService(executor)
                session.service = service

                service.sendResponse(ctx, 0, serverSeed)
                ctx.read()
            }

            is InitJs5Message -> {
                // TODO: version verification

                val service = Js5Service(js5Responses)
                session.service = service

                service.sendResponse(ctx, 0)
                ctx.read()
            }

            else -> throw UnsupportedOperationException("Unsupported message: $message")
        }
    }

    init {
        setDecoder(14, InitLoginMessageDecoder)
        setDecoder(15, InitJs5MessageDecoder)
    }

}