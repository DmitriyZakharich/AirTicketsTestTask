package com.example.air_tickets.domain

interface OffersRepository {
    fun getOffers(): List<OfferData>
}