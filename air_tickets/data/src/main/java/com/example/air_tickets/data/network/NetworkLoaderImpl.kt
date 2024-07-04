package com.example.air_tickets.data.network

import com.example.air_tickets.data.entities.FullListTicketsEntity
import com.example.air_tickets.data.entities.MainScreenOffersEntity
import com.example.air_tickets.data.entities.ShortListTicketsEntities
import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.response_results.ResponseResult

class NetworkLoaderImpl(private val apiService: ApiService) : NetworkLoader {

    override suspend fun loadMainScreenOffers(): ResponseResult {
        val response = apiService.getMainScreenOffers()
        return if (response.isSuccessful && response.body() != null) {
            ResponseResult.Success<MainScreenOffersEntity>(response.body()!!)
        } else {
            ResponseResult.Failure
        }
    }

    override suspend fun loadShortListTickets(): ResponseResult {
        val response = apiService.getShortListTickets()
        return if (response.isSuccessful && response.body() != null) {
            ResponseResult.Success<ShortListTicketsEntities>(response.body()!!)
        } else {
            ResponseResult.Failure
        }
    }

    override suspend fun loadFullListTickets(): ResponseResult {
        val response = apiService.getFullListTickets()
        return if (response.isSuccessful && response.body() != null) {
            ResponseResult.Success<FullListTicketsEntity>(response.body()!!)
        } else {
            ResponseResult.Failure
        }
    }
}