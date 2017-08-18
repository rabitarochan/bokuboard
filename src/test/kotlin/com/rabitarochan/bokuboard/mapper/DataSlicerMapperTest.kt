package com.rabitarochan.bokuboard.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabitarochan.bokuboard.entity.DataCharset
import com.rabitarochan.bokuboard.entity.DataDelimiter
import kotassert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.system.measureTimeMillis

@RunWith(SpringRunner::class)
@SpringBootTest
class DataSlicerMapperTest {

    @Autowired
    lateinit var dataSlicerMapper: DataSlicerMapper

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun test() {
        TODO()
    }

}
