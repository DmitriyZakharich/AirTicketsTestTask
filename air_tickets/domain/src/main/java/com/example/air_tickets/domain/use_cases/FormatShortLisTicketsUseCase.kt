package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.models.ShortDataTicketView
import com.example.air_tickets.domain.models.mapToView

class FormatShortLisTicketsUseCase {

    fun execute(list: List<ShortDataTicketModel>): List<ShortDataTicketView> =
        list.map { it.mapToView() }
}