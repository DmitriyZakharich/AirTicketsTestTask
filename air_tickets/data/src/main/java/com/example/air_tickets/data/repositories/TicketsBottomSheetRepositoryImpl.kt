package com.example.air_tickets.data.repositories

import com.example.air_tickets.domain.repositories.ItemInfoModel
import com.example.air_tickets.domain.repositories.TicketsBottomSheetRepository

data class ItemInfoEntity(val imageResource: Int, val stringResource: Int)

fun ItemInfoEntity.mapToDomain() = ItemInfoModel(
    imageResource = imageResource,
    stringResource = stringResource
)

class TicketsBottomSheetRepositoryImpl : TicketsBottomSheetRepository {

    override suspend fun getListShortcutsInfo(): List<ItemInfoModel> {
        return listOf(
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_difficult_route,
                stringResource = com.example.base.R.string.difficult_route
            ),
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_anywhere,
                stringResource = com.example.base.R.string.anywhere
            ),
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_weekend,
                stringResource = com.example.base.R.string.weekend
            ),
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_hot_tickets,
                stringResource = com.example.base.R.string.hot_tickets
            )
        ).map { it.mapToDomain() }
    }

    override suspend fun getListPopularDestinationsInfo(): List<ItemInfoModel> {
        return listOf(
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_istanbul,
                stringResource = com.example.base.R.string.istanbul
            ),
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_sochi,
                stringResource = com.example.base.R.string.sochi
            ),
            ItemInfoEntity(
                imageResource = com.example.base.R.drawable.image_phuket,
                stringResource = com.example.base.R.string.phuket
            )
        ).map { it.mapToDomain() }
    }
}