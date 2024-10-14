package com.ricky.adocaoapp.presentation.home

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.Usuario

data class HomeState(
    var isLoading:Boolean = false,
    var isLoadingMore:Boolean = false,
    val loadMoreVisible: Boolean = true,
    var pets:List<Pet> = emptyList(),
    var search:String = "",
    var usuario: Usuario = Usuario(),
    var filtroSearch: FiltroSearch = FiltroSearch(),
    val page: Int = 0,
    val error:String = "",
    var lat:Double = 0.0,
    var long:Double = 0.0,
)
