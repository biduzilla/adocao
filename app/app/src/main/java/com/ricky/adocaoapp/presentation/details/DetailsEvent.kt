package com.ricky.adocaoapp.presentation.details

sealed interface DetailsEvent {
    data object ClearError : DetailsEvent
}