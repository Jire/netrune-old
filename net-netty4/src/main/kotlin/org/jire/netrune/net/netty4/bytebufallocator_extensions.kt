package org.jire.netrune.net.netty4

import io.netty.buffer.ByteBufAllocator

fun ByteBufAllocator.fixedBuffer(capacity: Int) = buffer(capacity, capacity)

fun ByteBufAllocator.fixedHeapBuffer(capacity: Int) = heapBuffer(capacity, capacity)

fun ByteBufAllocator.fixedDirectBuffer(capacity: Int) = directBuffer(capacity, capacity)
