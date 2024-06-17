package com.example.air_tickets.data

class NetworkLoaderImpl(private val retrofitHelper: RetrofitHelper): NetworkLoader {

    override suspend fun loadUsersList(): OffersResponseResult {
        val apiService = retrofitHelper.getRetrofit().create(ApiService::class.java)
        val response = apiService.getOffers()

        return if (response.isSuccessful && response.body() != null) {
            OffersResponseResult.Success(response.body()!!)
        } else {
            OffersResponseResult.Failure
        }
    }
}