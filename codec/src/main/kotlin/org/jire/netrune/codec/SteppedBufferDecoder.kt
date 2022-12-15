package org.jire.netrune.codec

import org.jire.netrune.buffer.*
import org.jire.netrune.common.MutableSteps

abstract class SteppedBufferDecoder : BufferDecoder {

    val steps = MutableSteps()

    override fun decode() {
        steps.run()
    }

    inline fun step(crossinline run: () -> Unit) = steps.done(run)

    inline fun <T : ReadableBufferDelegate> stepDelegate(construct: () -> T): T {
        val delegate = construct()
        step {
            delegate.read(input)
        }
        return delegate
    }

    private operator fun <T : ReadableBufferDelegate> Function0<T>.unaryPlus() = stepDelegate(::invoke)

    val byte get() = +::ReadableBufferByteDelegate
    val byteA get() = +::ReadableBufferByteADelegate
    val byteC get() = +::ReadableBufferByteCDelegate
    val byteS get() = +::ReadableBufferByteSDelegate

    val uByte get() = +::ReadableBufferUByteDelegate
    val uByteA get() = +::ReadableBufferUByteADelegate
    val uByteC get() = +::ReadableBufferUByteCDelegate
    val uByteS get() = +::ReadableBufferUByteSDelegate

    val short get() = +::ReadableBufferShortDelegate
    val shortA get() = +::ReadableBufferShortADelegate
    val shortC get() = +::ReadableBufferShortCDelegate
    val shortS get() = +::ReadableBufferShortSDelegate

    val uShort get() = +::ReadableBufferUShortDelegate
    val uShortA get() = +::ReadableBufferUShortADelegate
    val uShortC get() = +::ReadableBufferUShortCDelegate
    val uShortS get() = +::ReadableBufferUShortSDelegate

    val shortLe get() = +::ReadableBufferShortLeDelegate
    val shortLeA get() = +::ReadableBufferShortLeADelegate
    val shortLeC get() = +::ReadableBufferShortLeCDelegate
    val shortLeS get() = +::ReadableBufferShortLeSDelegate

    val uShortLe get() = +::ReadableBufferUShortLeDelegate
    val uShortLeA get() = +::ReadableBufferUShortLeADelegate
    val uShortLeC get() = +::ReadableBufferUShortLeCDelegate
    val uShortLeS get() = +::ReadableBufferUShortLeSDelegate

    val int get() = +::ReadableBufferIntDelegate
    val intLe get() = +::ReadableBufferIntLeDelegate
    val intAlt3 get() = +::ReadableBufferIntAlt3Delegate
    val intAlt3Reversed get() = +::ReadableBufferIntAlt3ReversedDelegate

    val uInt get() = +::ReadableBufferUIntDelegate
    val uIntLe get() = +::ReadableBufferUIntLeDelegate

    val varInt get() = +::ReadableBufferVarIntDelegate

    val intSmart get() = +::ReadableBufferIntSmartDelegate
    val uIntSmart get() = +::ReadableBufferUIntSmartDelegate

    val long get() = +::ReadableBufferLongDelegate
    val longLe get() = +::ReadableBufferLongLeDelegate

    val string get() = +::ReadableBufferStringDelegate
    val versionedString get() = +::ReadableBufferVersionedStringDelegate

    fun skipBytes(length: Int) = ReadableBufferSkipDelegate(length)

}
