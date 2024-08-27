package com.ricky.adocaoapp.presentation.auth.register

sealed interface RegisterEvent {
    data class OnChangeNome(val nome: String) : RegisterEvent
    data class OnChangeTelefone(val telefone: String) : RegisterEvent
    data class OnChangeEmail(val email: String) : RegisterEvent
    data class OnChangeSenha(val senha: String) : RegisterEvent
    data class OnChangeConfirmSenha(val senha: String) : RegisterEvent
    data object CreateAccount : RegisterEvent
}