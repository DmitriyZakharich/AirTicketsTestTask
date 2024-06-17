package com.example.air_tickets.domain

interface OffersRepository {
    suspend fun getOffers(): OffersModel
}