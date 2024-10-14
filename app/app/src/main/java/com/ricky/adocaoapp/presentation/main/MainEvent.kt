package com.ricky.adocaoapp.presentation.main

import com.ricky.adocaoapp.presentation.home.HomeEvent

sealed interface MainEvent {
    data object OnSair: MainEvent
    data object ClearError : MainEvent
}