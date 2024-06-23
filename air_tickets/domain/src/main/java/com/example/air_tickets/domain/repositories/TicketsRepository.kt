package com.example.air_tickets.domain.repositories

import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.models.TicketModel

interface TicketsRepository {
    suspend fun getShortListTickets(): List<ShortDataTicketModel>
    suspend fun getFullListTickets(): List<TicketModel>
}