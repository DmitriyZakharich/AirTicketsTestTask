package com.example.air_tickets.domain.models

data class ShortDataTicketModel(
    val id: Int = 0,
    val priceModel: PriceModel = PriceModel(),
    val timeRange: List<String> = listOf(),
    val title: String = ""
)

data class PriceModel(
    val value: Int = 0
)