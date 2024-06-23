package com.example.air_tickets.data.entities

import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.PriceModel

data class MainScreenOffersEntity(
    val offers: List<Offer?>?
)

data class Offer(
    val id: Int?,
    val price: Price?,
    val title: String?,
    val town: String?
)

data class Price(
    val value: Int?
)

fun Offer.mapToDomain() = OfferModel(id = id, priceModel = price?.mapToDomain(), title = title, town = town)

fun Price?.mapToDomain() = PriceModel(value = this?.value ?: 0)