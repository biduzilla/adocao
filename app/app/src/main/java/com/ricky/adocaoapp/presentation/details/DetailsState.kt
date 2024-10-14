package com.ricky.adocaoapp.presentation.details

import com.ricky.adocaoapp.domain.models.Pet

data class DetailsState(
    val pet: Pet = Pet(),
    val userId: String="",
    val error: String = "",
    val isLoading: Boolean = true,
    val lat:Double = 0.0,
    val long:Double = 0.0,
)
