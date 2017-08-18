package com.rabitarochan.bokuboard.config

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.jackson.JsonComponentModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(createJavaTimeModule())
        objectMapper.registerModule(KotlinModule())
        objectMapper.registerModule(JsonComponentModule())
        return objectMapper
    }

    // private

    private fun createJavaTimeModule(): Module {
        val module = JavaTimeModule()
        module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(LocalDateTimeFormatPattern))
        module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(LocalDateTimeFormatPattern))
        return module
    }

    companion object {

        val LocalDateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")

    }

}
