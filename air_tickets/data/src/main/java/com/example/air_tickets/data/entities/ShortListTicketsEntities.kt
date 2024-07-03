package com.example.air_tickets.data.entities

import com.example.air_tickets.domain.models.PriceModel
import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.google.gson.annotations.SerializedName

data class ShortListTicketsEntities(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketsOffer?>?
) {
    companion object {
        fun getMock(): ShortListTicketsEntities = ShortListTicketsEntities(
            listOf(
                TicketsOffer(
                    id = 0,
                    price = Price(value = 2390),
                    title = "Уральские авиалинии",
                    timeRange = listOf("07:00", "09:10", "10:00", "11:00", "12:00", "13:00")
                ),
                TicketsOffer(
                    id = 1,
                    price = Price(value = 2390),
                    title = "Победа",
                    timeRange = listOf("08:05", "09:55", "16:35", "18:55")
                ),
                TicketsOffer(
                    id = 2,
                    price = Price(value = 2390),
                    title = "NordStar",
                    timeRange = listOf("13:10")
                )
            )
        )
    }
}

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

    val newList = MutableList(this.size) { "" }

    this.forEachIndexed { index, s ->
        newList[index] = s ?: ""
    }
    return newList
}