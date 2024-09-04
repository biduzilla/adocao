package com.ricky.adocaoapp.presentation.auth.login

import com.ricky.adocaoapp.presentation.auth.register.RegisterEvent

sealed interface LoginEvent {

    data class OnChangeEmail(var email: String) : LoginEvent
    data class OnChangeSenha(var senha: String) : LoginEvent
    data object OnLogin : LoginEvent

    data object ClearError : LoginEvent

}