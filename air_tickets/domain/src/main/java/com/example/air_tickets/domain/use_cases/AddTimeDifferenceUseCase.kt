package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.models.TicketModel
import java.text.SimpleDateFormat
import kotlin.math.abs

class AddTimeDifferenceUseCase {

    fun execute(tickets: List<TicketModel>, dateFormat: String): List<TicketModel> {

        val simpleDateFormat = SimpleDateFormat(dateFormat)
        return tickets.map {
            val date1 = simpleDateFormat.parse(it.departureModel.date)
            val date2 = simpleDateFormat.parse(it.arrivalModel.date)

            val differenceInMilliSeconds = abs(date2.time - date1.time)

            val differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24
            val differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60

            val result = when (differenceInMinutes) {
                30L -> "$differenceInHours.5ч в пути"
                0L -> "${differenceInHours}ч в пути"
                else -> "${differenceInHours}ч${differenceInMinutes}м в пути"
            }
            it.copy(travelTime = result)
        }
    }
}