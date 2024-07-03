package com.example.air_tickets.domain.repositories

interface TicketsBottomSheetRepository {
    suspend fun getListShortcutsInfo(): List<ItemInfoModel>
    suspend fun getListPopularDestinationsInfo(): List<ItemInfoModel>
}

data class ItemInfoModel(val imageResource: Int, val stringResource: Int)