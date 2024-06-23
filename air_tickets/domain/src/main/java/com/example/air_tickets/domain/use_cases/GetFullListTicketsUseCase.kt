package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.models.TicketModel
import com.example.air_tickets.domain.repositories.TicketsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetFullListTicketsUseCase(private val ticketsRepository: TicketsRepository) {

    suspend fun execute(dispatcher: CoroutineContext = Dispatchers.IO): List<TicketModel> =
        withContext(dispatcher) {
            ticketsRepository.getFullListTickets()
        }
}