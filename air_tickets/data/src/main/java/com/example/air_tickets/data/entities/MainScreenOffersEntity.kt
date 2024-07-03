package com.example.air_tickets.data.entities

import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.PriceModel

data class MainScreenOffersEntity(
    val offers: List<Offer?>?
) {
    companion object {
        fun getMock(): MainScreenOffersEntity = MainScreenOffersEntity(
            listOf(
                Offer(
                    id = 0,
                    price = Price(value = 22264),
                    title = "Die Antwoord",
                    town = "Будапешт"
                ),
                Offer(
                    id = 1,
                    price = Price(value = 2390),
                    title = "Socrat&Lera",
                    town = "Санкт-Петербург"
                ),
                Offer(id = 2, price = Price(value = 2390), title = "Лампабикт", town = "Москва")
            )
        )
    }
}

data class Offer(
    val id: Int?, val price: Price?, val title: String?, val town: String?
)

data class Price(
    val value: Int?
)

fun Offer?.mapToDomain() =
    OfferModel(
        id = this?.id ?: 0,
        priceModel = this?.price.mapToDomain(),
        title = this?.title ?: "",
        town = this?.town ?: ""
    )

fun Price?.mapToDomain() = PriceModel(value = this?.value ?: 0)