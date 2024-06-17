package com.example.air_tickets.domain

data class OfferData(
    val id: Int?,
    val price: Price?,
    val title: String?,
    val town: String?
)

data class Price(
    val value: Int?
)