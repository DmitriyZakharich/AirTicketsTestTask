package com.example.air_tickets.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow

interface CacheFormDataStore {
    suspend fun saveData(string: String)
    fun getData(): Flow<String>
}