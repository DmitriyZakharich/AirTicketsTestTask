package com.example.air_tickets.data

import com.example.air_tickets.domain.OffersModel
import com.example.air_tickets.domain.OffersRepository

class OfferRepositoryImpl(private val networkLoader: NetworkLoader) : OffersRepository {

    override suspend fun getOffers(): OffersModel {

        return when (val responseResult = networkLoader.loadUsersList()) {
            OffersResponseResult.Failure -> OffersModel(offers = null)
            is OffersResponseResult.Success -> responseResult.offers.mapToDomain()
        }
    }
}