package com.example.air_tickets.domain.models

data class ShortDataTicketModel(
    val id: Int = 0,
    val priceModel: PriceModel = PriceModel(),
    val timeRange: List<String> = listOf(),
    val title: String = ""
)

data class ShortDataTicketView(
    val id: Int = 0,
    val price: String = "",
    val timeRange: String = "",
    val title: String = ""
)

fun ShortDataTicketModel.mapToView() = ShortDataTicketView(
    id = id,
    price = priceModel.value.priceFormat().addArrowPriceString(),
    timeRange = timeRange.joinToString(separator = "  "),
    title = title
)

data class PriceModel(
    val value: Int = 0
)