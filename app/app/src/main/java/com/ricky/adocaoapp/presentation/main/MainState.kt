package com.ricky.adocaoapp.presentation.main

import com.ricky.adocaoapp.domain.models.Usuario

data class MainState(
    var usuario:Usuario = Usuario(),
    var error:String = "",
    var isLoading:Boolean = false,
    var onSair:Boolean = false
)
