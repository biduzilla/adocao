package com.ricky.adocaoapp.presentation.auth.forget_password

sealed interface ForgetPassEvent {
    data class OnChangeEmail(val email:String):ForgetPassEvent
    data class OnChangeCod(val cod:String):ForgetPassEvent
    data class OnChangeSenha(val senha:String):ForgetPassEvent
    data class OnChangeConfirmSenha(val senha:String):ForgetPassEvent
    data object OnSendEmail:ForgetPassEvent
    data object OnSendCod:ForgetPassEvent
    data object OnUpdatePassword:ForgetPassEvent

    data object ClearError : ForgetPassEvent
}