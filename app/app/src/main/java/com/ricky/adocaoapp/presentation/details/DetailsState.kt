package com.ricky.adocaoapp.presentation.details

import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.Usuario

data class DetailsState(
    val pet: Pet = Pet(),
    val usuario: Usuario = Usuario(),
    val error: String = "",
    val isLoading: Boolean = false
)
