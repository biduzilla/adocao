package com.ricky.adocaoapp.presentation.home

import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.Usuario

data class HomeState(
    var isLoading:Boolean = false,
    var usuario:Usuario = Usuario(),
    var pets:List<Pet> = emptyList(),
    var search:String = "",
)
