package com.example.air_tickets.data.network

import com.example.air_tickets.data.entities.FullListTicketsEntity
import com.example.air_tickets.data.entities.MainScreenOffersEntity
import com.example.air_tickets.data.entities.ShortListTicketsEntities
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getMainScreenOffers(): Response<MainScreenOffersEntity>

    //страна выбрана. предварительное отображение
    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getShortListTickets(): Response<ShortListTicketsEntities>

    //Посмотреть всё. экран с всеми офферами
    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getFullListTickets(): Response<FullListTicketsEntity>

}