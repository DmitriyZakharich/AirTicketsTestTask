package com.example.air_tickets.presentation.country_selected_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.air_tickets.domain.extensions.timeFormatting
import com.example.air_tickets.domain.models.DataModel
import com.example.air_tickets.domain.models.ShortDataTicketModel
import com.example.air_tickets.domain.use_cases.GetCurrentDataUseCase
import com.example.air_tickets.domain.use_cases.GetShortListTicketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountrySelectedViewModel @Inject constructor(
    private val getShortListTicketsUseCase: GetShortListTicketsUseCase,
    private val getCurrentDataUseCase: GetCurrentDataUseCase
) : ViewModel() {

    private val _tickets: MutableStateFlow<List<ShortDataTicketModel>> = MutableStateFlow(listOf())
    val tickets: Flow<List<ShortDataTicketModel>> = _tickets

    private val _time: MutableStateFlow<DataModel> = MutableStateFlow(DataModel())
    val time: Flow<DataModel> = _time

    fun loadTickets() {
        viewModelScope.launch(Dispatchers.Main) {
            _tickets.emit(getShortListTicketsUseCase.execute())
        }
    }

    fun loadCurrentTime(pattern: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _time.emit(getCurrentDataUseCase.execute(pattern))
        }
    }

    fun timeFormatting(time: Long, pattern: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _time.emit(DataModel(time, time.timeFormatting(pattern)))
        }
    }
}