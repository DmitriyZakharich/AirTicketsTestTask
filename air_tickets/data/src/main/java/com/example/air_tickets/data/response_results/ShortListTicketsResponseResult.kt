package com.example.air_tickets.data.response_results

import com.example.air_tickets.data.entities.ShortListTicketsEntities

sealed interface ShortListTicketsResponseResult {
    data class Success(val tickets: ShortListTicketsEntities): ShortListTicketsResponseResult
    data object Failure: ShortListTicketsResponseResult
}