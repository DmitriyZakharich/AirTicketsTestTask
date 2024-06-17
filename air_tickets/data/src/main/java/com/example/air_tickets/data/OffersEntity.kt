package com.example.air_tickets.data

import com.example.air_tickets.domain.Offer
import com.example.air_tickets.domain.OffersModel
import com.example.air_tickets.domain.Price

data class OffersEntity(
    val offerEntities: List<OfferEntity?>?
)

data class OfferEntity(
    val id: Int?,
    val priceEntity: PriceEntity?,
    val title: String?,
    val town: String?
)

data class PriceEntity(
    val value: Int?
)

fun OffersEntity.mapToDomain() : OffersModel {
    return OffersModel(offers = offerEntities?.map{offer -> offer?.mapToDomain()})
}


fun OfferEntity.mapToDomain() = Offer(id = id, price = priceEntity?.mapToDomain(), title = title, town = town)

fun PriceEntity.mapToDomain() = Price(value = value)