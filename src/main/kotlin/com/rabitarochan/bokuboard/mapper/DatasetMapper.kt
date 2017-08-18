package com.rabitarochan.bokuboard.mapper

import com.rabitarochan.bokuboard.entity.Dataset
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface DatasetMapper {

    @Select("""
        select
            *
        from
            t_dataset
    """)
    fun findAll(): List<Dataset>

    @Select("""
        select
            *
        from
            t_dataset
        where
            id = #{id}
    """)
    fun findOneById(id: String): Dataset?

    @Insert("""
        insert into
            t_dataset
        values (
            #{id}, #{name}, #{description},
            #{delimiter}, #{charset}, #{datasetType},
            #{fileSize}, #{recordCount}, #{digest},
            #{createdAt}, #{updatedAt}
        )
    """)
    fun insert(dataset: Dataset): Int

}
