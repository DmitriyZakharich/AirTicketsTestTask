package com.example.air_tickets.data.repositories

import com.example.air_tickets.data.entities.FullListTicketsEntity
import com.example.air_tickets.data.entities.ShortListTicketsEntities
import com.example.air_tickets.data.entities.mapToDomain
import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.response_results.ResponseResult
import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.models.TicketModel
import com.example.air_tickets.domain.repositories.TicketsRepository

class TicketsRepositoryImpl(private val networkLoader: NetworkLoader) : TicketsRepository {

    override suspend fun getShortListTickets(): List<ShortDataTicketModel> {
        return when (val responseResult = networkLoader.loadShortListTickets()) {
            ResponseResult.Failure -> {
                listOf()
            }
            is ResponseResult.Success<*> -> {
                if (responseResult.data is ShortListTicketsEntities) {
                    responseResult.data.ticketsOffers?.map {
                        it?.mapToDomain() ?: ShortDataTicketModel() } ?: listOf()
                } else {
                    listOf()
                }
            }
        }
    }

    override suspend fun getFullListTickets(): List<TicketModel> {
        return when (val responseResult = networkLoader.loadFullListTickets()) {
            ResponseResult.Failure -> {
                listOf()
            }
            is ResponseResult.Success<*> -> {
                if (responseResult.data is FullListTicketsEntity){
                    responseResult.data.tickets?.map { it.mapToDomain() } ?: listOf()
                } else {
                    listOf()
                }
            }
        }
    }
}