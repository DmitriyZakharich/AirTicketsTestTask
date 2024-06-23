package com.example.air_tickets.domain.use_cases

class RouteFormattingUseCase {

    fun execute(placeDeparture: String?, placeArrival: String?): String = StringBuilder()
            .append(placeDeparture ?: "")
            .append('-')
            .append(placeArrival ?: "")
            .toString()
}