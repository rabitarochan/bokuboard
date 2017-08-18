package com.rabitarochan.bokuboard.mapper

import com.rabitarochan.bokuboard.entity.DataCharset
import com.rabitarochan.bokuboard.entity.DataDelimiter
import com.rabitarochan.bokuboard.entity.Dataset
import com.rabitarochan.bokuboard.entity.DatasetType
import kotassert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
class DatasetMapperTest {

    @Autowired
    lateinit var datasetMapper: DatasetMapper

    @Test
    @Transactional
    fun testInsert() {
        val dataset = testDataset()

        val count = datasetMapper.insert(dataset)

        count.Is(1)
    }

    @Test
    @Transactional
    fun findAllTest() {
        val dataset = testDataset()
        datasetMapper.insert(dataset)

        val datasets = datasetMapper.findAll()

        datasets.size.Is(1)
        datasets.get(0).Is(dataset)
    }

    private fun testDataset(): Dataset {
        return Dataset(
            "ID",
            "NAME",
            "DESCRIPTION",
            DataDelimiter.Tab,
            DataCharset.UTF8,
            DatasetType.CSV,
            12345L,
            67890L,
            "CHECKSUM",
            LocalDateTime.of(2017, 1, 1, 12, 34, 56, 789000000),
            LocalDateTime.of(2017, 12, 31, 23, 59, 59, 999000000))
    }

}
