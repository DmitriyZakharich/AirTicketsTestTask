package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.OfferView
import com.example.air_tickets.domain.models.mapToView

class FormatOffersUseCase {

    fun execute(list: List<OfferModel>): List<OfferView> = list.map {
        it.mapToView()
    }
}