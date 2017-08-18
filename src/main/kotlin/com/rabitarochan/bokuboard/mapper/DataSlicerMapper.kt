package com.rabitarochan.bokuboard.mapper

import com.rabitarochan.bokuboard.entity.DataCharset
import com.rabitarochan.bokuboard.entity.DataDelimiter
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.jdbc.SQL
import org.apache.ibatis.session.RowBounds

@Mapper
interface DataSlicerMapper {

    @SelectProvider(type = DataSlicerSqlProvider::class, method = "table")
    fun table(filePath: String, charset: DataCharset, delimiter: DataDelimiter, limit: Int, page: Int): List<Map<String, Any>>

    class DataSlicerSqlProvider {

        fun table(filePath: String, charset: DataCharset, delimiter: DataDelimiter, limit: Int, page: Int): String {
            val sqlList = mutableListOf(
                "SELECT * FROM",
                "CSVREAD('${filePath}', NULL, '${charset.label}', ${delimiter.delimiterDBString})",
                "LIMIT ${limit}"
            )
            if (page > 1) {
                sqlList.add("OFFSET ${limit * (page - 1)}")
            }

            return sqlList.joinToString(" ")
        }

    }

}
