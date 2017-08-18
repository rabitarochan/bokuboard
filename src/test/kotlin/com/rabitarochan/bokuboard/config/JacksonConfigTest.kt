package com.rabitarochan.bokuboard.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabitarochan.bokuboard.entity.DataCharset
import com.rabitarochan.bokuboard.entity.DataDelimiter
import com.rabitarochan.bokuboard.entity.Dataset
import com.rabitarochan.bokuboard.entity.DatasetType
import com.rabitarochan.bokuboard.infrastructure.annotation.NoArgsConstructor
import kotassert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
class JacksonConfigTest {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun localDateTimeSerializeTest() {
        val dt = LocalDateTime.of(2011, 9, 12, 12, 34, 56, 789000000)
        val expected = "\"2011-09-12T12:34:56.789\""

        val actual = objectMapper.writeValueAsString(dt)

        actual.Is(expected)
    }

    @Test
    fun localDateTimeDeserializeTest() {
        val dts = "\"2011-09-12T12:34:56.789\""
        val expected = LocalDateTime.of(2011, 9, 12, 12, 34, 56, 789000000)

        val actual = objectMapper.readValue(dts, LocalDateTime::class.java)

        actual.Is(expected)
    }

    @Test
    fun kotlinDataClassSerializeTest() {
        val t = Person("Alice", 17)

        val actual = objectMapper.writeValueAsString(t)

        actual.Is("{\"name\":\"Alice\",\"age\":17}")
    }

    @Test
    fun kotlinDataClassDeserializeTest() {
        val json = "{\"name\":\"Bob\",\"age\":22}"

        val actual = objectMapper.readValue(json, Person::class.java)

        actual.Is(Person("Bob", 22))
    }

    @Test
    fun kotlinEnumSerializeTest() {
        val ds = testDataset()

        val actual = objectMapper.writeValueAsString(ds)
        val expected = testDatasetJson()

        actual.Is(expected)
    }

    @Test
    fun kotlinEnumDeserializeTest() {
        val actual = objectMapper.readValue(testDatasetJson(), Dataset::class.java)
        val expected = testDataset()

        actual.Is(expected)
    }

    @NoArgsConstructor
    data class Person(val name: String, val age: Int)

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
            "DIGEST",
            LocalDateTime.of(2017, 1, 1, 12, 34, 56, 789000000),
            LocalDateTime.of(2017, 12, 31, 23, 59, 59, 999000000))
    }

    private fun testDatasetJson(): String =
        """{"id":"ID","name":"NAME","description":"DESCRIPTION","delimiter":"Tab","charset":"UTF8","datasetType":"CSV","fileSize":12345,"recordCount":67890,"digest":"DIGEST","createdAt":"2017-01-01T12:34:56.789","updatedAt":"2017-12-31T23:59:59.999"}"""

}
