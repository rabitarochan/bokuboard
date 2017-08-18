package com.rabitarochan.bokuboard.util

import org.springframework.util.DigestUtils
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

object FileUtil {

    fun getRecordCount(filePath: String, charset: Charset): Long {
        val file = Paths.get(filePath)
        val count = Files.lines(file, charset).use { it.count() - 1 } // ignore header
        return count
    }

    fun getDigest(filePath: String): String {
        val digest = File(filePath).inputStream().use { inStream ->
            DigestUtils.md5DigestAsHex(inStream)
        }
        return digest
    }

}
