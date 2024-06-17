package com.example.air_tickets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.air_tickets.domain.GetOffersUseCase
import com.example.air_tickets.domain.GetPlaceDepartureUseCase
import com.example.air_tickets.domain.OfferData
import com.example.air_tickets.domain.SavePlaceDepartureUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AirTicketsViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getPlaceDepartureUseCase: GetPlaceDepartureUseCase,
    private val savePlaceDepartureUseCase: SavePlaceDepartureUseCase,
) : ViewModel() {

    val flowPlaceDeparture: Flow<String> = getPlaceDepartureUseCase.execute()

    private val _listOfferData: MutableStateFlow<List<OfferData>> = MutableStateFlow(listOf())
    val flowListOfferData: Flow<List<OfferData>> = _listOfferData

    fun savePlaceDeparture(data: String) {
        viewModelScope.launch(Dispatchers.Main) {
            savePlaceDepartureUseCase.execute(data)
        }
    }

    fun getOffers() {
        viewModelScope.launch(Dispatchers.Main) {
            _listOfferData.emit(getOffersUseCase.execute())
        }
    }
}