package com.ricky.adocaoapp.presentation.home

import com.ricky.adocaoapp.domain.models.FiltroSearch

sealed interface HomeEvent {
    data object OnSair:HomeEvent
    data class OnChangeFiltro(val filtro:FiltroSearch):HomeEvent
    data class OnChangePesquisa(val pesquisa:String):HomeEvent
}