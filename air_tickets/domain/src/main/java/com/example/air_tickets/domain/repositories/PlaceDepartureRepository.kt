package com.example.air_tickets.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PlaceDepartureRepository {
    suspend fun save(data: String)
    fun getData(): Flow<String>
}