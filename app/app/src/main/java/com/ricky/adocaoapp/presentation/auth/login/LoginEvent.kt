package com.ricky.adocaoapp.presentation.auth.login

sealed interface LoginEvent {

    data class OnChangeEmail(var email: String) : LoginEvent
    data class OnChangeSenha(var senha: String) : LoginEvent
    data object OnLogin : LoginEvent

}