package com.rabitarochan.bokuboard.service

import com.rabitarochan.bokuboard.entity.DataCharset
import com.rabitarochan.bokuboard.entity.DataDelimiter
import com.rabitarochan.bokuboard.entity.Dataset
import com.rabitarochan.bokuboard.entity.DatasetType
import com.rabitarochan.bokuboard.mapper.DatasetMapper
import com.rabitarochan.bokuboard.util.FileUtil
import com.rabitarochan.bokuboard.util.IdUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.charset.Charset
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class DatasetService(
    private val datasetMapper: DatasetMapper,
    private val directoryService: DirectoryService
) {

    fun getById(id: String): Dataset? {
        return datasetMapper.findOneById(id)
    }

    fun getAll(): List<Dataset> {
        return datasetMapper.findAll()
    }

    @Transactional(readOnly = false)
    fun createDataset(
        name: String,
        description: String,
        delimiter: DataDelimiter,
        charset: DataCharset,
        datasetType: DatasetType,
        file: MultipartFile
    ): Dataset {
        val id = createNewId()
        val destPath = getDatasetPath(id)
        val destFile = putFile(file, destPath)
        val fileInfo = checkFile(destFile, charset.charset)

        val now = LocalDateTime.now()
        val dataset = Dataset(
            id, name, description, delimiter, charset, datasetType,
            fileInfo.fileSize, fileInfo.recordCount, fileInfo.digest,
            now, now)

        val insertCount = datasetMapper.insert(dataset)
        assert(insertCount == 1)

        return dataset
    }

    // private

    private data class FileInfo(val fileSize: Long, val recordCount: Long, val digest: String)

    private fun createNewId(): String {
        return IdUtil.newIdByUUID(16)
    }

    private fun getDatasetPath(id: String): String {
        val dir = directoryService.getDatasetDir(id)
        return "${dir}/${id}.txt"
    }

    private fun putFile(srcFile: MultipartFile, destPath: String): File {
        val destFile = File(destPath)
        srcFile.transferTo(destFile)
        return destFile
    }

    private fun checkFile(destFile: File, charset: Charset): FileInfo {
        val fileSize = destFile.length()
        val recordCount = FileUtil.getRecordCount(destFile.absolutePath, charset)
        val digest = FileUtil.getDigest(destFile.absolutePath)
        return FileInfo(fileSize, recordCount, digest)
    }

}
