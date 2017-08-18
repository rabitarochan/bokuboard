package com.rabitarochan.bokuboard.util

import java.util.*

object IdUtil {

    fun newIdByUUID(length: Int): String {
        val sb = StringBuilder()
        while (sb.length < length) {
            val uuid = newUUID().replace("-", "")
            sb.append(uuid)
        }
        return sb.toString().substring(0, length)
    }

    fun newUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun newIdByEpoch(): Long {
        return System.currentTimeMillis()
    }

}
