package com.example.air_tickets.domain.use_cases

import com.example.air_tickets.domain.repositories.ItemInfoModel
import com.example.air_tickets.domain.repositories.TicketsBottomSheetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetListShortcutsInfoUseCase(
    private val ticketsBottomSheetRepository: TicketsBottomSheetRepository
) {
    suspend fun execute(dispatcher: CoroutineContext = Dispatchers.IO): List<ItemInfoModel> =
        withContext(dispatcher) {
            ticketsBottomSheetRepository.getListShortcutsInfo()
        }
}

