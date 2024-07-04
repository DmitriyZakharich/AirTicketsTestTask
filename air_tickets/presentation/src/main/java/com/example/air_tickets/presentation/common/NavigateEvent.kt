package com.example.air_tickets.presentation.common

import android.os.Bundle

sealed class NavigateEvent {
    data object PopBackStack : NavigateEvent()
    data class NavigateTo(val id: Int, val bundle: Bundle? = null) : NavigateEvent()
}