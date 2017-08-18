package com.rabitarochan.bokuboard.entity

enum class DataDelimiter(val delimiterString: String, val delimiterDBString: String) {
    Comma(",", "','"),
    Tab("\t", "char(9)")
}
