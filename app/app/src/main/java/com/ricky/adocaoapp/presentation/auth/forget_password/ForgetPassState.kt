package com.ricky.adocaoapp.presentation.auth.forget_password

data class ForgetPassState(
    var email: String = "",
    var senha: String = "",
    var cod: String = "",
    var confirmSenha: String = "",
    var isLoading: Boolean = false,
    var isEmailSend: Boolean = false,
    var isCodVer: Boolean = false,
    var isOk: Boolean = false,
    var onErrorEmail:Boolean = false,
    var onErrorCod:Boolean = false,
    var onErrorSenha:Boolean = false,
    var onErrorConfirmSenha:Boolean = false,
)
