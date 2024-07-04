package com.example.air_tickets.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class NavigateViewModel @Inject constructor(): ViewModel() {

    protected val _navigateState: MutableStateFlow<NavigateState> = MutableStateFlow(NavigateState.Idle)
    val navigateState: Flow<NavigateState> = _navigateState

    open fun navigate(navigateEvent: NavigateEvent) {
        viewModelScope.launch(Dispatchers.Main) {
            _navigateState.emit(
                when (navigateEvent) {
                    is NavigateEvent.NavigateTo -> NavigateState.NavigateTo(navigateEvent.id, navigateEvent.bundle)
                    NavigateEvent.PopBackStack -> NavigateState.PopBackStack
                }
            )
            _navigateState.emit(NavigateState.Idle) //обнулить состояние, чтобы избежать автоматического перехода
                                                    //при возвращении на этот экран
        }
    }
}