package com.example.air_tickets.data.network

import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.response_results.FullListTicketsResponseResult
import com.example.air_tickets.data.response_results.MainScreenOffersResponseResult
import com.example.air_tickets.data.response_results.ShortListTicketsResponseResult

class NetworkLoaderImpl(retrofitHelper: RetrofitHelper): NetworkLoader {

    private val apiService = retrofitHelper.getRetrofit().create(ApiService::class.java)

    override suspend fun loadMainScreenOffers(): MainScreenOffersResponseResult {
        val response = apiService.getMainScreenOffers()
        return if (response.isSuccessful && response.body() != null) {
            MainScreenOffersResponseResult.Success(response.body()!!)
        } else {
            MainScreenOffersResponseResult.Failure
        }
    }

    override suspend fun loadShortListTickets(): ShortListTicketsResponseResult {
        val response = apiService.getShortListTickets()
        return if (response.isSuccessful && response.body() != null) {
            ShortListTicketsResponseResult.Success(response.body()!!)
        } else {
            ShortListTicketsResponseResult.Failure
        }
    }

    override suspend fun loadFullListTickets(): FullListTicketsResponseResult {
        val response = apiService.getFullListTickets()
        return if (response.isSuccessful && response.body() != null) {
            FullListTicketsResponseResult.Success(response.body()!!)
        } else {
            FullListTicketsResponseResult.Failure
        }
    }
}