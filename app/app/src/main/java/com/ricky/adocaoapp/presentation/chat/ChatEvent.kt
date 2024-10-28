package com.ricky.adocaoapp.presentation.chat

sealed interface ChatEvent {
    data object ClearError : ChatEvent
    data object Resume : ChatEvent
}