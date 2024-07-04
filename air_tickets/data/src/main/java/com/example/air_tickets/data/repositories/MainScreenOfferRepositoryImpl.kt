package com.example.air_tickets.data.repositories

import com.example.air_tickets.data.entities.MainScreenOffersEntity
import com.example.air_tickets.data.entities.mapToDomain
import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.response_results.ResponseResult
import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.repositories.MainScreenOffersRepository

class MainScreenOfferRepositoryImpl(private val networkLoader: NetworkLoader) :
    MainScreenOffersRepository {

    override suspend fun getMainScreenOffers(): List<OfferModel> {
        return when (val responseResult = networkLoader.loadMainScreenOffers()) {
            ResponseResult.Failure -> {
                listOf()
            }
            is ResponseResult.Success<*> -> {
                if (responseResult.data is MainScreenOffersEntity) {
                    responseResult.data.offers?.map { it?.mapToDomain() ?: OfferModel() } ?: listOf()
                } else {
                    listOf()
                }
            }
        }
    }
}