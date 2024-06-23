package com.example.air_tickets.data.entities

import com.example.air_tickets.domain.models.PriceModel
import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.google.gson.annotations.SerializedName

data class ShortListTicketsEntities(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketsOffer?>?
)

data class TicketsOffer(
    val id: Int?,
    val price: Price?,
    @SerializedName("time_range")
    val timeRange: List<String?>?,
    val title: String?
)

fun TicketsOffer.mapToDomain() = ShortDataTicketModel(
    id = id ?: 0,
    priceModel = price?.mapToDomain() ?: PriceModel(value = 0),
    timeRange = timeRange.mapToDomain(),
    title = title ?: ""
)

fun List<String?>?.mapToDomain(): List<String> {

    if (this == null) {
        return listOf()
    }

    val newList = MutableList(this.size){""}

    this.forEachIndexed { index, s ->
        newList[index] = s ?: ""
    }
    return newList
}