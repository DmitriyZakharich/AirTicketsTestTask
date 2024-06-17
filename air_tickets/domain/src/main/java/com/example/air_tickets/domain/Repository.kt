package com.example.air_tickets.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun save(data: String)
    fun getData(): Flow<String>
}