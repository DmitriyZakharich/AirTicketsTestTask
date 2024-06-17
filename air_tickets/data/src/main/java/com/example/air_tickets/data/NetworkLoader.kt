package com.example.air_tickets.data

interface NetworkLoader {
    suspend fun loadUsersList(): OffersResponseResult
}
