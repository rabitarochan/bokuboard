package com.rabitarochan.bokuboard.entity

import com.rabitarochan.bokuboard.infrastructure.annotation.NoArgsConstructor
import java.time.LocalDateTime

@NoArgsConstructor
data class Dataset(
    val id: String,
    val name: String,
    val description: String,
    val delimiter: DataDelimiter,
    val charset: DataCharset,
    val datasetType: DatasetType,
    val fileSize: Long,
    val recordCount: Long,
    val digest: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
