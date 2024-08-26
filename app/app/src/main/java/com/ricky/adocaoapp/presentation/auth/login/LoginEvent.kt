package com.ricky.adocaoapp.presentation.auth.login

sealed interface LoginEvent {

    data class OnChangeEmail(var email: String) : LoginEvent
}