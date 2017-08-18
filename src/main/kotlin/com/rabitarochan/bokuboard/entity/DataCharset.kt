package com.rabitarochan.bokuboard.entity

import java.nio.charset.Charset

enum class DataCharset(val label: String, val charset: Charset) {
    UTF8("UTF-8", Charsets.UTF_8),
    ShiftJis("Shift-JIS", Charset.forName("Windows-31J"))
}
