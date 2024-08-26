package com.ricky.adocaoapp.presentation.auth.login

data class LoginState(
    var isLoading: Boolean = false,
    var email: String = "",
    var senha: String = "",
    var error: String = "",
    var onLogin: Boolean = false,
    var onErrorEmail: Boolean = false,
    var onErrorSenha: Boolean = false,
)
