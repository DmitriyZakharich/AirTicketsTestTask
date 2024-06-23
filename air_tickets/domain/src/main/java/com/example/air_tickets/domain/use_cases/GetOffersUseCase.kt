package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.repositories.MainScreenOffersRepository
import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.TicketModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetOffersUseCase(private val mainScreenOffersRepository: MainScreenOffersRepository) {

    suspend fun execute(dispatcher: CoroutineContext = Dispatchers.IO): List<OfferModel?>? =
        withContext(dispatcher) {
            mainScreenOffersRepository.getMainScreenOffers()
        }
}