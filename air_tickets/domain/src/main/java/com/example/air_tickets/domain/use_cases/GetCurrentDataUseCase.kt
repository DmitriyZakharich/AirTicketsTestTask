package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.extensions.timeFormatting
import com.example.air_tickets.domain.models.DataModel
import java.text.SimpleDateFormat
import java.util.Date

class GetCurrentDataUseCase {

    fun execute(pattern: String): DataModel {
        val time = Date().time
        return DataModel(time, time.timeFormatting(pattern))
    }
}