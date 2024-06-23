package com.example.air_tickets.domain.models

import java.util.Locale

data class OfferModel(
    val id: Int? = 0,
    val priceModel: PriceModel? = PriceModel(),
    val title: String? = "",
    val town: String? = ""
)

data class OfferView(
    val id: Int? = 0,
    val price: String = "",
    val title: String? = "",
    val town: String? = ""
)

fun OfferModel.mapToView(): OfferView = OfferView(
    id = id,
    price = priceModel?.value?.priceFormat() ?: "0 ₽",
    title = title,
    town = town
)

fun Int.priceFormat(): String = String
    .format(Locale.US, "%,d", this)
    .replace(",", " ")
    .plus(" ₽")


