package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.repositories.TicketsRepository

class GetShortListTicketsUseCase(private val ticketsRepository: TicketsRepository) {

    suspend fun execute(): List<ShortDataTicketModel> = ticketsRepository.getShortListTickets()
}