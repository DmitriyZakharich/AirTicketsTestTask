package com.example.air_tickets.domain

class GetOffersUseCase(private val offersRepository: OffersRepository) {
    fun execute(): List<OfferData> = offersRepository.getOffers()
}