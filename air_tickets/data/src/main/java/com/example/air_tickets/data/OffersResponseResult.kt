package com.example.air_tickets.data

sealed interface OffersResponseResult {
    data class Success(val offers: OffersEntity): OffersResponseResult
    data object Failure: OffersResponseResult
}