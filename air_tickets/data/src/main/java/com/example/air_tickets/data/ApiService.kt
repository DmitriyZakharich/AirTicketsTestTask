package com.example.air_tickets.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffers(): Response<OffersEntity>

//    @GET("users/{login}")
//    suspend fun getUserDetails(
//        @Path("login") login: String
//    ): Response<UserDetailsData>
}