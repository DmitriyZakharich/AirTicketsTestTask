package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.repositories.TicketsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetShortListTicketsUseCase(private val ticketsRepository: TicketsRepository) {

    suspend fun execute(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<ShortDataTicketModel> =
        withContext(dispatcher) {
            ticketsRepository.getShortListTickets()
        }
}