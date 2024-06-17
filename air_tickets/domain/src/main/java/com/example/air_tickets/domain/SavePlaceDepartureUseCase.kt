package com.example.air_tickets.domain

class SavePlaceDepartureUseCase(private val repository: Repository) {
    suspend fun execute(data: String)  {
        repository.save(data)
    }
}