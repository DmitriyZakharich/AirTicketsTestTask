package com.example.air_tickets.domain.use_cases

class FormattingDateNumberSeatsUseCase {

    fun execute(data: String, numberSeats: Int?): String = StringBuilder()
        .append(data)
        .append(", ")
        .append(numberSeats ?: "")
        .append(" пассажир")
        .toString()
}