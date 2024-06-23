package com.example.air_tickets.data.repositories.interfaces

import com.example.air_tickets.data.response_results.FullListTicketsResponseResult
import com.example.air_tickets.data.response_results.MainScreenOffersResponseResult
import com.example.air_tickets.data.response_results.ShortListTicketsResponseResult

interface NetworkLoader {
    suspend fun loadMainScreenOffersList(): MainScreenOffersResponseResult
    suspend fun loadShortListTickets(): ShortListTicketsResponseResult
    suspend fun loadFullListTickets(): FullListTicketsResponseResult
}
