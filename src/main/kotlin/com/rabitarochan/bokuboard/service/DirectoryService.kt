package com.rabitarochan.bokuboard.service

import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Service
class DirectoryService {

    fun getRootDir(): String = "data"

    fun getDatasetDir(datasetId: String): String {
        return mkdirs(getRootDir(), "datasets", datasetId)
    }

    fun pathOf(vararg path: String): String {
        return path.joinToString(File.separator)
    }

    fun mkdirs(vararg path: String): String {
        val dir = pathOf(*path)
        val dirPath = Paths.get(dir)
        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath)
        }
        return dir
    }

}
