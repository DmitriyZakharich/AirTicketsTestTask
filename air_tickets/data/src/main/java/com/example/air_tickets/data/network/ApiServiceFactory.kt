package com.example.air_tickets.data.network

import com.example.air_tickets.data.BuildConfig

class ApiServiceFactory(private val retrofitHelper: RetrofitHelper) {
    fun createApiService(): ApiService =
        if (BuildConfig.DEBUG) {
            MockApiService()
        } else {
            retrofitHelper.getRetrofit().create(ApiService::class.java)
        }
}