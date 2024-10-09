package com.ricky.adocaoapp.presentation.details

import com.ricky.adocaoapp.presentation.home.HomeEvent

sealed interface DetailsEvent {
    data object ClearError : DetailsEvent
}