package org.jire.netrune.bytes

import net.openhft.chronicle.bytes.Bytes
import net.openhft.chronicle.bytes.StreamingDataInput

private const val NULL = '\u0000'
private const val REPLACEMENT = '?'

private val codepage = charArrayOf(
    // 0x80
    '€', NULL, '‚', 'ƒ', '„', '…', '†', '‡',
    'ˆ', '‰', 'Š', '‹', 'Œ', NULL, 'Ž', NULL,

    // 0x90
    NULL, '‘', '’', '“', '”', '•', '–', '—',
    '˜', '™', 'š', '›', 'œ', NULL, 'ž', 'Ÿ'
)

fun StreamingDataInput<*>.readCp1252(b: Bytes<*>) = read8bit(b)

fun StreamingDataInput<*>.readCp1252() = read8bit()
