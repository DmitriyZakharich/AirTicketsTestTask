package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.repositories.PlaceDepartureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetPlaceDepartureUseCase(private val placeDepartureRepository: PlaceDepartureRepository) {

    fun execute(): Flow<String> = placeDepartureRepository.getData()
}