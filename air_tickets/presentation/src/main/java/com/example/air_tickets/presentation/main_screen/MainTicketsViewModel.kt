package com.example.air_tickets.presentation.main_screen

import androidx.lifecycle.viewModelScope
import com.example.air_tickets.domain.models.OfferModel
import com.example.air_tickets.domain.models.OfferView
import com.example.air_tickets.domain.use_cases.FormatOffersUseCase
import com.example.air_tickets.domain.use_cases.GetOffersUseCase
import com.example.air_tickets.domain.use_cases.GetPlaceDepartureUseCase
import com.example.air_tickets.domain.use_cases.SavePlaceDepartureUseCase
import com.example.air_tickets.presentation.common.NavigateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTicketsViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val formatOffersUseCase: FormatOffersUseCase,
    private val savePlaceDepartureUseCase: SavePlaceDepartureUseCase,
    getPlaceDepartureUseCase: GetPlaceDepartureUseCase
) : NavigateViewModel() {

    val flowPlaceDeparture: Flow<String> = getPlaceDepartureUseCase.execute()

    private val _listOfferModelData: MutableStateFlow<List<OfferView>> = MutableStateFlow(listOf())
    val flowListOfferModelData: Flow<List<OfferView>> = _listOfferModelData

    fun savePlaceDeparture(data: String) {
        viewModelScope.launch(Dispatchers.Main) {
            savePlaceDepartureUseCase.execute(data)
        }
    }

    fun getOffers() {
        viewModelScope.launch(Dispatchers.Main) {
            val offerModelList: List<OfferModel> = getOffersUseCase.execute()
            val formattedList = formatOffersUseCase.execute(offerModelList)
            _listOfferModelData.emit(formattedList)
        }
    }
}