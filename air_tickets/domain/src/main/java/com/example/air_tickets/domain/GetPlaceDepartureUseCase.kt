package com.example.air_tickets.domain

import kotlinx.coroutines.flow.Flow

class GetPlaceDepartureUseCase(private val repository: Repository) {

    fun execute(): Flow<String> = repository.getData()
}