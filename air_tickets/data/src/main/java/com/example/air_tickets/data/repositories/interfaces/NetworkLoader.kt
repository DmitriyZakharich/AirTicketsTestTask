package com.example.air_tickets.data.repositories.interfaces

import com.example.air_tickets.data.response_results.ResponseResult

interface NetworkLoader {
    suspend fun loadMainScreenOffers(): ResponseResult
    suspend fun loadShortListTickets(): ResponseResult
    suspend fun loadFullListTickets(): ResponseResult
}
