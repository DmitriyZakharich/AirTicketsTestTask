package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.repositories.PlaceDepartureRepository

class SavePlaceDepartureUseCase(private val placeDepartureRepository: PlaceDepartureRepository) {
    suspend fun execute(data: String)  {
        placeDepartureRepository.save(data)
    }
}