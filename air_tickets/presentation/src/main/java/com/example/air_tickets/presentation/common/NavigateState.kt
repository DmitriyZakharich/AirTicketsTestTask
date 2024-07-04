package com.example.air_tickets.presentation.common

import android.os.Bundle

sealed class NavigateState {
    data object Idle: NavigateState()
    data object PopBackStack : NavigateState()
    data class NavigateTo(val id: Int, val bundle: Bundle?) : NavigateState()
}