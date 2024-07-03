package com.example.air_tickets.data.entities

import com.example.air_tickets.domain.models.ArrivalModel
import com.example.air_tickets.domain.models.DepartureModel
import com.example.air_tickets.domain.models.HandLuggageModel
import com.example.air_tickets.domain.models.LuggageModel
import com.example.air_tickets.domain.models.PriceModel
import com.example.air_tickets.domain.models.TicketModel
import com.google.gson.annotations.SerializedName

data class FullListTicketsEntity(
    val tickets: List<Ticket>?
) {
    companion object {
        fun getMock(): FullListTicketsEntity = FullListTicketsEntity(List(10) {
            Ticket(
                arrival = ArrivalEntity(
                    airport = "airport",
                    date = "2000-10-03T10:00:00",
                    town = "town"
                ),
                badge = "badge",
                company = "company",
                departure = DepartureEntity(
                    airport = "airport", date = "2000-10-03T00:00:00", town = "town"
                ),
                handLuggage = HandLuggageEntity(hasHandLuggage = true, size = ""),
                hasTransfer = true,
                hasVisaTransfer = true,
                id = 0,
                isExchangable = true,
                isReturnable = true,
                luggage = LuggageEntity(hasLuggage = true, price = Price(2000)),
                price = Price(2000),
                providerName = ""
            )
        })
    }
}

data class Ticket(
    @SerializedName("arrival") val arrival: ArrivalEntity?,
    val badge: String?,
    val company: String?,
    @SerializedName("departure") val departure: DepartureEntity?,
    @SerializedName("hand_luggage") val handLuggage: HandLuggageEntity?,
    @SerializedName("has_transfer") val hasTransfer: Boolean?,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean?,
    val id: Int?,
    @SerializedName("is_exchangable") val isExchangable: Boolean?,
    @SerializedName("is_returnable") val isReturnable: Boolean?,
    @SerializedName("luggage") val luggage: LuggageEntity?,
    val price: Price?,
    @SerializedName("provider_name") val providerName: String?
)

data class ArrivalEntity(
    val airport: String?, val date: String?, val town: String?
)

data class DepartureEntity(
    val airport: String?, val date: String?, val town: String?
)

data class HandLuggageEntity(
    @SerializedName("has_hand_luggage") val hasHandLuggage: Boolean?, val size: String?
)

data class LuggageEntity(
    @SerializedName("has_luggage") val hasLuggage: Boolean?, val price: Price?
)

fun Ticket.mapToDomain() = TicketModel(
    arrivalModel = arrival?.mapToDomain() ?: ArrivalModel(),
    badge = badge ?: "",
    company = company ?: "",
    departureModel = departure?.mapToDomain() ?: DepartureModel(),
    handLuggageModel = handLuggage?.mapToDomain() ?: HandLuggageModel(),
    hasTransfer = hasTransfer ?: false,
    hasVisaTransfer = hasVisaTransfer ?: false,
    id = id ?: 0,
    isExchangable = isExchangable ?: false,
    isReturnable = isReturnable ?: false,
    luggageModel = luggage?.mapToDomain() ?: LuggageModel(),
    price = price?.mapToDomain() ?: PriceModel(),
    providerName = providerName ?: ""
)

fun ArrivalEntity.mapToDomain() = ArrivalModel(
    airport = airport ?: "", date = date ?: "", town = town ?: ""
)

fun DepartureEntity.mapToDomain() = DepartureModel(
    airport = airport ?: "", date = date ?: "", town = town ?: ""
)

fun HandLuggageEntity.mapToDomain() = HandLuggageModel(
    hasHandLuggage = hasHandLuggage ?: false, size = size ?: ""
)

fun LuggageEntity.mapToDomain() = LuggageModel(
    hasLuggage = hasLuggage ?: false
)