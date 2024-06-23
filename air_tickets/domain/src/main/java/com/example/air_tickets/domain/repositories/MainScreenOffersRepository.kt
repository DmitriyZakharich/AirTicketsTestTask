package com.example.air_tickets.domain.repositories

import com.example.air_tickets.domain.models.OfferModel

interface MainScreenOffersRepository {
    suspend fun getMainScreenOffers(): List<OfferModel?>?
}