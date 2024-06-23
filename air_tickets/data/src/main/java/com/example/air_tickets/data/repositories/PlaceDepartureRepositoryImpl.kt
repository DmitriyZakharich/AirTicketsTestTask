package com.example.air_tickets.data.repositories

import com.example.air_tickets.data.repositories.interfaces.CacheFormDataStore
import com.example.air_tickets.domain.repositories.PlaceDepartureRepository
import kotlinx.coroutines.flow.Flow

class PlaceDepartureRepositoryImpl(private val cacheFormDataStore: CacheFormDataStore) :
    PlaceDepartureRepository {

    override suspend fun save(data: String) {
        cacheFormDataStore.saveData(data)
    }

    override fun getData(): Flow<String> = cacheFormDataStore.getData()
}