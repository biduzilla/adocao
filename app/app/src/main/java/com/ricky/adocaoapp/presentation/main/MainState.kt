package com.ricky.adocaoapp.presentation.main

import com.ricky.adocaoapp.domain.models.Usuario

data class MainState(
    val user: Usuario = Usuario()
)
