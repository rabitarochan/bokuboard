package com.rabitarochan.bokuboard

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BokuboardApplication

fun main(args: Array<String>) {
    SpringApplication.run(BokuboardApplication::class.java, *args)
}
