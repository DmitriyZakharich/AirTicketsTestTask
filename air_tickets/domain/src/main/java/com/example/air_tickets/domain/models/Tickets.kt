package com.example.air_tickets.domain.models

data class TicketModel(
    val arrivalModel: ArrivalModel = ArrivalModel(),
    val badge: String = "",
    val company: String = "",
    val departureModel: DepartureModel = DepartureModel(),
    val handLuggageModel: HandLuggageModel = HandLuggageModel(),
    val hasTransfer: Boolean = true,
    val hasVisaTransfer: Boolean = false,
    val id: Int = 0,
    val isExchangable: Boolean = false,
    val isReturnable: Boolean = false,
    val luggageModel: LuggageModel = LuggageModel(),
    val price: PriceModel = PriceModel(),
    val providerName: String = "",
    val travelTime: String = ""
)

data class ArrivalModel(
    val airport: String = "",
    val date: String = "",
    val town: String = ""
)

data class DepartureModel(
    val airport: String = "",
    val date: String = "",
    val town: String = ""
)

data class HandLuggageModel(
    val hasHandLuggage: Boolean = false,
    val size: String = ""
)

data class LuggageModel(
    val hasLuggage: Boolean = false,
    val price: PriceModel = PriceModel()
)