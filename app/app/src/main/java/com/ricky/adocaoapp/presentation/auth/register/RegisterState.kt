package com.ricky.adocaoapp.presentation.auth.register

data class RegisterState(
    var loading: Boolean = false,
    var nome: String = "",
    var email: String = "",
    var telefone: String = "",
    var senha: String = "",
    var confirmarSenha: String = "",
    var onErrorNome: Boolean = false,
    var onErrorEmail: Boolean = false,
    var onErrorTelefone: Boolean = false,
    var onErrorSenha: Boolean = false,
    var onErrorConfirmarSenha: Boolean = false,
)
