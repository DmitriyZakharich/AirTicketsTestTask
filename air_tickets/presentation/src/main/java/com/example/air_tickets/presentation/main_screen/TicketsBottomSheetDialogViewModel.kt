package com.example.air_tickets.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.OfferView
import com.example.air_tickets.domain.models.mapToView
import com.example.air_tickets.domain.repositories.ItemInfoModel
import com.example.air_tickets.domain.use_cases.GetListPopularDestinationsUseCase
import com.example.air_tickets.domain.use_cases.GetListShortcutsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsBottomSheetDialogViewModel @Inject constructor(
    private val getListShortcutsInfoUseCase: GetListShortcutsInfoUseCase,
    private val getListPopularDestinationsUseCase: GetListPopularDestinationsUseCase
) : ViewModel() {

    private val _listShortcutsInfo: MutableStateFlow<List<ItemInfoModel>> = MutableStateFlow(listOf())
    val flowListShortcutsInfo: Flow<List<ItemInfoModel>> = _listShortcutsInfo

    private val _listPopularDestinations: MutableStateFlow<List<ItemInfoModel>> = MutableStateFlow(listOf())
    val flowListPopularDestinations: Flow<List<ItemInfoModel>> = _listPopularDestinations

    fun getListShortcutsInfo() {
        viewModelScope.launch(Dispatchers.Main) {
            val list: List<ItemInfoModel> = getListShortcutsInfoUseCase.execute()
            _listShortcutsInfo.emit(list)
        }
    }

    fun getListPopularDestinations() {
        viewModelScope.launch(Dispatchers.Main) {
            val list: List<ItemInfoModel> = getListPopularDestinationsUseCase.execute()
            _listPopularDestinations.emit(list)
        }
    }
}