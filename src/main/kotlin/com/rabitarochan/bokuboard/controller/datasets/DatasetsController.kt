package com.rabitarochan.bokuboard.controller.datasets

import com.rabitarochan.bokuboard.service.DatasetService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DatasetsController(
    private val datasetService: DatasetService
) {

    @GetMapping("datasets")
    fun indexPage(): String = "index"

    @GetMapping("datasets.json")
    @ResponseBody
    fun indexApi(): Any {
        val datasets = datasetService.getAll()
        return mapOf(
            "items" to datasets,
            "count" to datasets.size
        )
    }

}
