package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.repositories.PlaceDepartureRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SavePlaceDepartureUseCase(private val placeDepartureRepository: PlaceDepartureRepository) {
    suspend fun execute(data: String, dispatcher: CoroutineDispatcher = Dispatchers.IO)  {
        withContext(dispatcher) {
            placeDepartureRepository.save(data)
        }
    }
}