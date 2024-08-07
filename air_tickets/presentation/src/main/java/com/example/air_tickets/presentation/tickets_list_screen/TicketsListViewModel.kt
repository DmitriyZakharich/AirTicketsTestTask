package com.example.air_tickets.presentation.tickets_list_screen

import androidx.lifecycle.viewModelScope
import com.example.air_tickets.domain.extensions.timeFormatting
import com.example.air_tickets.domain.models.TicketModel
import com.example.air_tickets.domain.use_cases.AddTimeDifferenceUseCase
import com.example.air_tickets.domain.use_cases.FormatDateNumberSeatsUseCase
import com.example.air_tickets.domain.use_cases.GetFullListTicketsUseCase
import com.example.air_tickets.domain.use_cases.RouteFormattingUseCase
import com.example.air_tickets.presentation.common.NavigateEvent
import com.example.air_tickets.presentation.common.NavigateState
import com.example.air_tickets.presentation.common.NavigateViewModel
import com.example.air_tickets.presentation.common.TIME_FORMAT_2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsListViewModel @Inject constructor(
    private val getFullListTicketsUseCase: GetFullListTicketsUseCase,
    private val routeFormattingUseCase: RouteFormattingUseCase,
    private val formatDateNumberSeatsUseCase: FormatDateNumberSeatsUseCase,
    private val addTimeDifferenceUseCase: AddTimeDifferenceUseCase
) : NavigateViewModel() {

    private val _tickets: MutableStateFlow<List<TicketModel>> = MutableStateFlow(listOf())
    val tickets: Flow<List<TicketModel>> = _tickets

    private val _route: MutableStateFlow<String> = MutableStateFlow("")
    val route: Flow<String> = _route

    private val _dateNumberSeats: MutableStateFlow<String> = MutableStateFlow("")
    val dateNumberSeats: Flow<String> = _dateNumberSeats

    fun loadTickets() {
        viewModelScope.launch(Dispatchers.Main) {

            val tickets = getFullListTicketsUseCase.execute()
            val processedTickets = addTimeDifferenceUseCase.execute(tickets, "yyyy-MM-dd'T'HH:mm:ss")
            _tickets.emit(processedTickets)
        }
    }

    fun setData(placeDeparture: String?, placeArrival: String?, data: Long?, numberSeats: Int?) {
        viewModelScope.launch(Dispatchers.Main) {
            _route.emit(routeFormattingUseCase.execute(placeDeparture, placeArrival))
        }

        viewModelScope.launch(Dispatchers.Main) {
            val dataFormated = data?.timeFormatting(TIME_FORMAT_2) ?: ""
            _dateNumberSeats.emit(formatDateNumberSeatsUseCase.execute(dataFormated, numberSeats))
        }
    }

    override fun navigate(navigateEvent: NavigateEvent) {
        viewModelScope.launch(Dispatchers.Main) {
            _navigateState.emit(
                when (navigateEvent) {
                    is NavigateEvent.NavigateTo -> NavigateState.Idle
                    NavigateEvent.PopBackStack -> NavigateState.PopBackStack
                }
            )
            _navigateState.emit(NavigateState.Idle) //обнулить состояние, чтобы избежать автоматического перехода
                                                    //при возвращении на этот экран
        }
    }
}