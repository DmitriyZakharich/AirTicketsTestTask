package com.example.air_tickets.data

import com.example.air_tickets.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val cacheFormDataStore: CacheFormDataStore) : Repository {
    override suspend fun save(data: String) {
        cacheFormDataStore.saveData(data)
    }

    override fun getData(): Flow<String> = cacheFormDataStore.getData()
}