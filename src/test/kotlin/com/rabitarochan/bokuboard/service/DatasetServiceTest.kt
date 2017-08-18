package com.rabitarochan.bokuboard.service

import com.rabitarochan.bokuboard.entity.DataCharset
import com.rabitarochan.bokuboard.entity.DataDelimiter
import com.rabitarochan.bokuboard.entity.DatasetType
import com.rabitarochan.bokuboard.mapper.DataSlicerMapper
import com.rabitarochan.bokuboard.util.FileUtil
import kotassert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.io.File

@RunWith(SpringRunner::class)
@SpringBootTest
class DatasetServiceTest {

    @Autowired
    lateinit var datasetService: DatasetService

    @Autowired
    lateinit var dataSlicerMapper: DataSlicerMapper

    @Test
    @Transactional
    fun test() {
        TODO()
    }

}
