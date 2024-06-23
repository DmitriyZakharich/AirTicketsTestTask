package com.example.air_tickets.data.response_results

import com.example.air_tickets.data.entities.MainScreenOffersEntity

sealed interface MainScreenOffersResponseResult {
    data class Success(val offers: MainScreenOffersEntity): MainScreenOffersResponseResult
    data object Failure: MainScreenOffersResponseResult
}