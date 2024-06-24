package com.example.air_tickets.data.repositories

import com.example.air_tickets.data.entities.mapToDomain
import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.response_results.MainScreenOffersResponseResult
import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.repositories.MainScreenOffersRepository

class MainScreenOfferRepositoryImpl(private val networkLoader: NetworkLoader) :
    MainScreenOffersRepository {

    override suspend fun getMainScreenOffers(): List<OfferModel?>? {

        return when (val responseResult = networkLoader.loadMainScreenOffers()) {
            MainScreenOffersResponseResult.Failure -> {
                listOf()
            }

            is MainScreenOffersResponseResult.Success -> {
                val list = responseResult.offers.offers?.map { it?.mapToDomain() }
                list ?: listOf()
            }
        }
    }
}