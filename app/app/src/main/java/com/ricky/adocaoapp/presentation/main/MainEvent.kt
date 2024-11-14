package com.ricky.adocaoapp.presentation.main

sealed interface MainEvent {
    data object OnSair: MainEvent
    data object ClearError : MainEvent
    data object Resume : MainEvent
}