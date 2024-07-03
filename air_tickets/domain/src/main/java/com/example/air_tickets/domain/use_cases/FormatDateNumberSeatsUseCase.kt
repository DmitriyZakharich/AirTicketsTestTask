package com.example.air_tickets.domain.use_cases

class FormatDateNumberSeatsUseCase {

    fun execute(data: String, numberSeats: Int?): String = StringBuilder()
        .append(data)
        .append(", ")
        .append(numberSeats ?: "")
        .append(" пассажир")
        .toString()
}