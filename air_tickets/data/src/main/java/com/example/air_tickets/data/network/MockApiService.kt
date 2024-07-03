package com.example.air_tickets.data.network

import com.example.air_tickets.data.entities.FullListTicketsEntity
import com.example.air_tickets.data.entities.MainScreenOffersEntity
import com.example.air_tickets.data.entities.ShortListTicketsEntities
import retrofit2.Response

class MockApiService : ApiService {
    override suspend fun getMainScreenOffers(): Response<MainScreenOffersEntity> {
        return Response.success(MainScreenOffersEntity.getMock())
    }

    override suspend fun getShortListTickets(): Response<ShortListTicketsEntities> {
        return Response.success(ShortListTicketsEntities.getMock())
    }

    override suspend fun getFullListTickets(): Response<FullListTicketsEntity> {
        return Response.success(FullListTicketsEntity.getMock())
    }
}