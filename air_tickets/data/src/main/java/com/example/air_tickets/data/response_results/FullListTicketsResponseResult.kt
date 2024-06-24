package com.example.air_tickets.data.response_results

import com.example.air_tickets.data.entities.FullListTicketsEntity

sealed interface FullListTicketsResponseResult {
    data class Success(val tickets: FullListTicketsEntity): FullListTicketsResponseResult
    data object Failure: FullListTicketsResponseResult
}