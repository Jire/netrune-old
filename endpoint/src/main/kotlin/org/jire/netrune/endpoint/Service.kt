package org.jire.netrune.endpoint

import io.netty.channel.ChannelHandlerContext
import kotlin.reflect.KClass

interface Service {

    fun setDecoder(opcode: Int, decoder: MessageDecoder<*>)

    fun getDecoder(opcode: Int): MessageDecoder<*>?

    fun handle(session: Session, ctx: ChannelHandlerContext, message: IncomingMessage)

    fun readComplete(session: Session, ctx: ChannelHandlerContext) {
    }

    fun writabilityChanged(session: Session, ctx: ChannelHandlerContext) {
    }

    fun <M : OutgoingMessage> setEncoder(type: Class<M>, encoder: MessageEncoder<M>)

    fun <M : OutgoingMessage> setEncoder(type: KClass<M>, encoder: MessageEncoder<M>) =
        setEncoder(type.java, encoder)

    fun <M : OutgoingMessage> getEncoder(type: Class<M>): MessageEncoder<M>?

    fun <M : OutgoingMessage> getEncoder(type: KClass<M>): MessageEncoder<M>? =
        getEncoder(type.java)

}