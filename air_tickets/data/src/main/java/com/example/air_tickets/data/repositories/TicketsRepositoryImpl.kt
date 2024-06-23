package com.example.air_tickets.data.repositories

import android.util.Log
import com.example.air_tickets.data.entities.mapToDomain
import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.response_results.FullListTicketsResponseResult
import com.example.air_tickets.data.response_results.ShortListTicketsResponseResult
import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.models.TicketModel
import com.example.air_tickets.domain.repositories.TicketsRepository

class TicketsRepositoryImpl(private val networkLoader: NetworkLoader) : TicketsRepository {

    override suspend fun getShortListTickets(): List<ShortDataTicketModel> {
        return when (val responseResult = networkLoader.loadShortListTickets()) {
            ShortListTicketsResponseResult.Failure -> {
                listOf()
            }
            is ShortListTicketsResponseResult.Success -> {
                responseResult.tickets.ticketsOffers?.map {it?.mapToDomain() ?: ShortDataTicketModel() } ?: listOf()
            }
        }
    }

    override suspend fun getFullListTickets(): List<TicketModel> {
        return when (val responseResult = networkLoader.loadFullListTickets()) {
            FullListTicketsResponseResult.Failure -> {
                listOf()
            }
            is FullListTicketsResponseResult.Success -> {
                responseResult.tickets.tickets?.map { it.mapToDomain() } ?: listOf()
            }
        }
    }
}